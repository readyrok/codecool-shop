package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.ShoppingCart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private Map<String, ShoppingCart> allshoppingCart = new HashMap<>();
    private static ShoppingCartDaoMem instance = null;

    ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void addShoppingCart(String userId, ShoppingCart shoppingCart) {
        this.allshoppingCart.put(userId, shoppingCart);
    }
    @Override
    public ShoppingCart find(String userId) {
        return allshoppingCart.get(userId);
    }

    @Override
    public Map<String, ShoppingCart> getAll() {
        return allshoppingCart;
    }
}
