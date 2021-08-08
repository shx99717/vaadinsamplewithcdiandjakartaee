package com.shx99717.vaadin.vaadinsamplewithcdiandjakartaee.views.vo;

public class Address {
    private String country;
    private String street;
    private String postcode;

    public Address() {}

    public Address(String country, String street, String postcode) {
        super();
        this.country = country;
        this.street = street;
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address [country=" + country + ", street=" + street + ", postcode=" + postcode + "]";
    }


}
