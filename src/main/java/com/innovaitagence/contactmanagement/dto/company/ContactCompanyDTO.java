package com.innovaitagence.contactmanagement.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovaitagence.contactmanagement.enums.ContactType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactCompanyDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private ContactType contactType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
