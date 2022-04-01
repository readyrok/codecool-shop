package com.codecool.shop.model;

import java.util.Currency;

public class LineItem {
    private String productName;
    private String supplier;
    private String productPrice;
    private String linePrice;
    private transient Product product;
    private int productId;
    private int quantity;
    private float price;
    private Currency currency;

    public LineItem(Product product){
        this.productName = product.getName();
        this.supplier = product.getSupplierName();
        this.product = product;
        this.productId = product.getId();
        this.productPrice = product.getPrice();
        quantity = 1;
        price = product.getDefaultPrice() * quantity;
        this.currency = product.getDefaultCurrency();
        this.linePrice = String.format("%.02f", price) + " " + currency.toString();

    }

    public Product getProduct() {
        return product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity += 1;
        price = product.getDefaultPrice() * quantity;
        linePrice = String.format("%.02f", price) + " " + product.getDefaultCurrency().toString();
    }

    public void decreaseQuantity() {
        quantity -= 1;
        price = product.getDefaultPrice() * quantity;
        linePrice = String.format("%.02f", price) + " " + product.getDefaultCurrency().toString();
    }

    public String getProductName() {
        return productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getLinePrice() {
        return linePrice;
    }
}
