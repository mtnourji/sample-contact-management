package com.innovaitagence.contactmanagement.service;

import com.innovaitagence.contactmanagement.dto.company.CompanyDTO;
import com.innovaitagence.contactmanagement.dto.company.CompanyUpdateDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getCompanies(String vat);

    CompanyDTO getCompany(Long id);

    CompanyDTO createCompany(CompanyUpdateDTO newCompany);

    void updateCompany(Long companyId, CompanyUpdateDTO companyUpdates);

    void addContact(Long companyId, Long contactId);
}
