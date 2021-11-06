package com.innovaitagence.contactmanagement.service.impl;

import com.innovaitagence.contactmanagement.converter.EntityMapper;
import com.innovaitagence.contactmanagement.dto.AddressDTO;
import com.innovaitagence.contactmanagement.dto.company.CompanyDTO;
import com.innovaitagence.contactmanagement.dto.company.CompanyUpdateDTO;
import com.innovaitagence.contactmanagement.model.Address;
import com.innovaitagence.contactmanagement.model.Company;
import com.innovaitagence.contactmanagement.model.Contact;
import com.innovaitagence.contactmanagement.repository.CompanyRepository;
import com.innovaitagence.contactmanagement.repository.ContactRepository;
import com.innovaitagence.contactmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCompanyService implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EntityMapper mapper;

    @Transactional
    @Override
    public List<CompanyDTO> getCompanies(final String vat) {
        final List<Company> companies;
        if(StringUtils.hasText(vat)){
            companies = repository.findByVatIgnoreCase(vat.trim());
        } else {
            companies = repository.findAll();
        }
        return mapper.mapAsList(companies, CompanyDTO.class);
    }

    @Transactional
    @Override
    public CompanyDTO getCompany(final Long id) {
        final Company company = findOrThrow(id);
        return mapper.map(company, CompanyDTO.class);
    }

    private Company findOrThrow(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Company with id [" + id + "] does not exist"));
    }

    @Transactional
    @Override
    public CompanyDTO createCompany(final CompanyUpdateDTO newCompany) {
        final Company createdCompany = saveOrUpdate(new Company(), newCompany);
        return mapper.map(createdCompany, CompanyDTO.class);
    }

    @Transactional
    @Override
    public void updateCompany(final Long companyId, final CompanyUpdateDTO companyUpdates) {
        saveOrUpdate(findOrThrow(companyId), companyUpdates);
    }

    @Transactional
    @Override
    public void addContact(final Long companyId, final Long contactId) {
        final Company company = findOrThrow(companyId);
        final Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact with id [" + contactId + "] does not exist"));
        company.getContacts().add(contact);
        repository.save(company);
    }

    private Company saveOrUpdate(final Company company, final CompanyUpdateDTO updates){
        company.setAddress(mapper.map(Optional.ofNullable(updates.getAddress()).orElse(new AddressDTO()), Address.class));
        company.setName(updates.getName());
        company.setLanguage(updates.getLanguage());
        company.setVat(updates.getVat());
        company.setModifiedOn(LocalDateTime.now());
        return repository.save(company);
    }
}
