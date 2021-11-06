package com.innovaitagence.contactmanagement.controller;

import com.innovaitagence.contactmanagement.dto.contact.ContactDTO;
import com.innovaitagence.contactmanagement.dto.contact.ContactUpdateDTO;
import com.innovaitagence.contactmanagement.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDTO>> getContacts(){
        final List<ContactDTO> contacts = contactService.getContacts();
        return ResponseEntity.ok(contacts);
    }

    @PostMapping("/contacts")
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody final ContactUpdateDTO newContact){
        final ContactDTO contact = contactService.createContact(newContact);
        return ResponseEntity.created(URI.create("/contacts/" + contact.getId())).body(contact);
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<Void> getContact(@PathVariable("contactId") final Long contactId,
                                           @Valid @RequestBody final ContactUpdateDTO updates){
        contactService.updateContact(contactId, updates);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable("contactId") final Long contactId){
        contactService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }
}
