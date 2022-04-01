package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCart;

import java.util.Map;


public interface ShoppingCartDao {
    void addShoppingCart(String userId, ShoppingCart shoppingCart);
    ShoppingCart find(String userId);
    Map<String, ShoppingCart> getAll();
}
