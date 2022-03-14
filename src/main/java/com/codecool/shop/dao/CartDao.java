package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.ArrayList;

public interface CartDao {
    void add(Product product);
    void removeProduct(Product product);
    void removeAllProducts(Product product);
    void clearCart();
    ArrayList<Product> getAllProducts();
}
