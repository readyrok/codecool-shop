package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;

public class CartDaoMem implements CartDao {
    private ArrayList<Product> cart = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem(){};

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        this.cart.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        for (Product p: cart ){
            if (p.getName().equals(product.getName())){
                cart.remove(p);
                break;
            }
        }
    }

    @Override
    public void removeAllProducts(Product product) {
        while(this.cart.contains(product)){
            this.cart.remove(product);
        }
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return cart;
    }
}
