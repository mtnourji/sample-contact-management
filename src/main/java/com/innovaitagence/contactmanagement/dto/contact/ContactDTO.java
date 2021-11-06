package com.innovaitagence.contactmanagement.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovaitagence.contactmanagement.dto.AddressDTO;
import com.innovaitagence.contactmanagement.dto.company.ContactCompanyDTO;
import com.innovaitagence.contactmanagement.enums.ContactType;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO {
    private Long id;
    private AddressDTO address;
    private String firstName;
    private String lastName;
    private ContactType contactType;
    private String vat;
    private List<ContactCompanyDTO> companies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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

    public List<ContactCompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<ContactCompanyDTO> companies) {
        this.companies = companies;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
