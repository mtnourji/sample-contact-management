package com.innovaitagence.contactmanagement.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.innovaitagence.contactmanagement.dto.AddressDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyUpdateDTO {
    @NotEmpty(message = "Please provide a name")
    private String name;
    @NotEmpty(message = "Please provide the vat")
    private String vat;
    private String language;
    @Valid
    @NotNull(message = "Please provide an address")
    private AddressDTO address;

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
}
