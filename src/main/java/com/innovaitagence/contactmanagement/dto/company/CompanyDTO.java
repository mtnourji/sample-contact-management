package com.innovaitagence.contactmanagement.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovaitagence.contactmanagement.dto.AddressDTO;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDTO {
    private Long id;
    private String name;
    private String vat;

    private String language;

    private AddressDTO address;

    private List<ContactCompanyDTO> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<ContactCompanyDTO> getContacts() {
        if (null == contacts) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    public void setContacts(List<ContactCompanyDTO> contacts) {
        this.contacts = contacts;
    }
}
