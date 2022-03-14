package com.codecool.shop.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> cart = new ArrayList<>();

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void add(Product product){ this.cart.add(product); }
}
