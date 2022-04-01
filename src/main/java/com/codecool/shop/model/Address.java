package com.codecool.shop.model;

public abstract class Address {
    private String country;
    private String city;
    private int zipcode;
    private String street;
    private int houseNumber;

    public Address(String country, String city, int zipcode, String street, int houseNumber) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
}
