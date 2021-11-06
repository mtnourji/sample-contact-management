package com.innovaitagence.contactmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {

    private String streetNumber;

    private String streetName;

    @NotEmpty(message = "Please provide a postal code")
    private String zip;

    @NotEmpty(message = "Please provide a city")
    private String city;

    private String countryIsoCode;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }
}
