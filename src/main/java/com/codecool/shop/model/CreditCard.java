package com.codecool.shop.model;

public class CreditCard {
    private String name;
    private String cardNumber;
    private String expiration;
    private String cvv;

    public CreditCard(String name, String cardNumber, String expiration, String cvv){
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getCvv() {
        return cvv;
    }
}
