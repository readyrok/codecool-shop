package com.codecool.shop.model;

public class CreditCard {
    private String name;
    private int cardNumber;
    private String expiration;
    private int cvv;

    public CreditCard(String name, int cardNumber, String expiration, int cvv){
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public int getCvv() {
        return cvv;
    }
}
