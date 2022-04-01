package com.codecool.shop.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int orderId;
    private Date date;
    private User user;
    private ShoppingCart shoppingCart;

    public Order(User user, ShoppingCart shoppingCart) {
        this.user = user;
        this.shoppingCart = shoppingCart;
        date = new Date(System.currentTimeMillis());

    }
    public String getFormatedDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return formatter.format(getDate());
    }

    public Date getDate() {
        return date;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
