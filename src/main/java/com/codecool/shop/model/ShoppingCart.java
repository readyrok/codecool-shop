package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class ShoppingCart {
    private List<LineItem> lineItems = new ArrayList<>();
    private Currency currency;
    private float totalPrice;
    private String subTotal;

    public ShoppingCart(Product product) {
        this.currency = product.getDefaultCurrency();
        addProduct(product);

    }

    public void addProduct(Product product) {
        LineItem lineItem;
        if (getLineItemByProduct(product) != null) {
            lineItem = getLineItemByProduct(product);
            lineItem.increaseQuantity();
        } else {
            lineItem = new LineItem(product);
            this.lineItems.add(lineItem);
        }
        totalPrice += lineItem.getProduct().getDefaultPrice();
        this.subTotal = String.format("%.02f", totalPrice);
    }

    public void removeLineItem(int productId) {
        LineItem lineItem = getLineItemByProductId(productId);
        totalPrice -= lineItem.getPrice();
        this.subTotal = String.format("%.02f", totalPrice);
        this.lineItems.remove(lineItem);

    }
    public void decreaseLineItem(int productId) {
        LineItem lineItem = getLineItemByProductId(productId);
        if(lineItem.getQuantity() == 1){
            removeLineItem(productId);
        } else {
            totalPrice -= lineItem.getProduct().getDefaultPrice();
            lineItem.decreaseQuantity();
        }
        System.out.println(lineItem.getPrice());
        this.subTotal = String.format("%.02f", totalPrice);
    }
    public void increaseLineItem(int productId) {
        LineItem lineItem = getLineItemByProductId(productId);
        lineItem.increaseQuantity();
        totalPrice += lineItem.getProduct().getDefaultPrice();
        this.subTotal = String.format("%.02f", totalPrice);
    }

    public LineItem getLineItemByProduct(Product product) {
        return lineItems.stream().filter(t -> t.getProduct().getId() == product.getId()).findFirst().orElse(null);
    }

    public LineItem getLineItemByProductId(int productId) {
        return lineItems.stream().filter(t -> t.getProduct().getId() == productId).findFirst().orElse(null);
    }
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public LineItem getLineItemsFirst() {
        return lineItems.get(0);
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void removeLineItem(LineItem lineItem) {
        this.lineItems.remove(lineItem);
    }

    public String getCurrency() {
        return currency.toString();
    }

    public String getSubTotal() {
        return subTotal;
    }
}
