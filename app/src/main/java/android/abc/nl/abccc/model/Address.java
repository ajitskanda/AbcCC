package android.abc.nl.abccc.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Address {
    private String street;
    private String number;
    private String zipcode;
    private String country;
    private String city;
    private String stateProvince;
    private boolean isPrimary;
    private String type;

    public Address() {

    }

    public Address(String street, String number, String zipcode, String country, String city, String stateProvince, boolean isPrimary, String type) {
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.country = country;
        this.city = city;
        this.stateProvince = stateProvince;
        this.isPrimary = isPrimary;
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

}

