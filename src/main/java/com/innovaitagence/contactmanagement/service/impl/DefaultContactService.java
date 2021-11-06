package com.innovaitagence.contactmanagement.service.impl;

import com.innovaitagence.contactmanagement.converter.EntityMapper;
import com.innovaitagence.contactmanagement.dto.AddressDTO;
import com.innovaitagence.contactmanagement.dto.contact.ContactDTO;
import com.innovaitagence.contactmanagement.dto.contact.ContactUpdateDTO;
import com.innovaitagence.contactmanagement.enums.ContactType;
import com.innovaitagence.contactmanagement.model.Address;
import com.innovaitagence.contactmanagement.model.Contact;
import com.innovaitagence.contactmanagement.repository.ContactRepository;
import com.innovaitagence.contactmanagement.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultContactService implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private EntityMapper mapper;

    @Override
    public List<ContactDTO> getContacts() {
        final List<Contact> contacts = repository.findAll();
        return mapper.mapAsList(contacts, ContactDTO.class);
    }

    @Override
    public void updateContact(final Long contactId, final ContactUpdateDTO updates) {
        final Contact contact = repository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact with id [" + contactId + "] does not exist"));
        saveOrUpdate(contact, updates);
    }

    @Override
    public ContactDTO createContact(final ContactUpdateDTO newContact) {
        final Contact contact = saveOrUpdate(new Contact(), newContact);
        return mapper.map(contact, ContactDTO.class);
    }

    @Override
    public void deleteContact(final Long contactId) {
        repository.deleteById(contactId);
    }

    private Contact saveOrUpdate(final Contact contact, final ContactUpdateDTO updates) {
        if(ContactType.FREELANCER.equals(updates.getContactType()) && !StringUtils.hasText(updates.getVat())){
            throw new IllegalArgumentException("A freelancer must have a vat number");
        } else if(ContactType.EMPLOYEE.equals(updates.getContactType()) && StringUtils.hasText(updates.getVat())){
            throw new IllegalArgumentException("An employee should not have a vat number");
        }
        contact.setAddress(mapper.map(Optional.ofNullable(updates.getAddress()).orElse(new AddressDTO()), Address.class));
        contact.setContactType(updates.getContactType());
        contact.setFirstName(updates.getFirstName());
        contact.setLastName(updates.getLastName());
        contact.setVat(updates.getVat());
        return repository.save(contact);
    }
}
