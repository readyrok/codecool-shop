package com.codecool.shop.model;

public class ShippingAddress extends Address {

    public ShippingAddress(String country, String city, int zipcode, String street, int houseNumber) {
        super(country, city, zipcode, street, houseNumber);
    }
}
