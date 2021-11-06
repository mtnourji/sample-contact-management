package com.innovaitagence.contactmanagement.service;

import com.innovaitagence.contactmanagement.dto.contact.ContactDTO;
import com.innovaitagence.contactmanagement.dto.contact.ContactUpdateDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getContacts();

    void updateContact(Long contactId, ContactUpdateDTO updates);

    ContactDTO createContact(ContactUpdateDTO newContact);

    void deleteContact(Long contactId);
}
