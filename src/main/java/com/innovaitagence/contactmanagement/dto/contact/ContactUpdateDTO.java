package com.innovaitagence.contactmanagement.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovaitagence.contactmanagement.dto.AddressDTO;
import com.innovaitagence.contactmanagement.enums.ContactType;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUpdateDTO {
    @NotNull(message = "Please provide an address")
    @Valid
    private AddressDTO address;

    @NotEmpty(message = "Please provide a first name")
    private String firstName;
    @NotEmpty(message = "Please provide a last name")
    private String lastName;
    @NotNull(message = "Please provide a contact type")
    private ContactType contactType;
    private String vat;

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

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
