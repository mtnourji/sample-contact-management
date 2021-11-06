package com.innovaitagence.contactmanagement.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger customExceptionLogger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        final String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        body.put("path", path);

        final List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        customExceptionLogger.debug("Bad request received on path {}: {}", path, errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> onIllegalArgumentException(final HttpServletRequest request, final IllegalArgumentException e) {
        customExceptionLogger.debug("Illegal argument received", e);

        final Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        final String path = request.getRequestURI();
        body.put("path", path);
        body.put("errors", Collections.singleton(e.getMessage()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
