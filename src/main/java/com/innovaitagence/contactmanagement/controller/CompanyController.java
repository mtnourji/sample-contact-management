package com.innovaitagence.contactmanagement.controller;

import com.innovaitagence.contactmanagement.dto.company.CompanyDTO;
import com.innovaitagence.contactmanagement.dto.company.CompanyUpdateDTO;
import com.innovaitagence.contactmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(@RequestParam(name="vat", required = false) final String vat) {
        final List<CompanyDTO> companies = companyService.getCompanies(vat);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable("companyId") final Long companyId) {
        final CompanyDTO company = companyService.getCompany(companyId);
        return ResponseEntity.ok(company);
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody final CompanyUpdateDTO newCompany) {
        final CompanyDTO createdCompany = companyService.createCompany(newCompany);
        return ResponseEntity.created(URI.create("/companies/" + createdCompany.getId())).body(createdCompany);
    }

    @PutMapping("/companies/{companyId}")
    public ResponseEntity<Void> updateCompany(@Valid@PathVariable("companyId") final Long companyId,
                                                    @RequestBody final CompanyUpdateDTO companyUpdates) {
        companyService.updateCompany(companyId, companyUpdates);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/companies/{companyId}/contacts/{contactId}")
    public ResponseEntity<Void> addContactToCompany(@PathVariable("companyId") final Long companyId,
                                                    @PathVariable("contactId") final Long contactId){
        companyService.addContact(companyId, contactId);
        return ResponseEntity.noContent().build();
    }
}
