package com.codecool.shop.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> cart;
    private static Cart instance = null;

    private Cart(){
        cart = new ArrayList<>();
    };

    public static Cart getInstance(){
        if(instance == null) instance = new Cart();
        return instance;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void add(Product product){
        this.cart.add(product);
    }
}
