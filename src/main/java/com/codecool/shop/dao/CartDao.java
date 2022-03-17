package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public interface CartDao {
    void add(Product product);
    void removeProduct(Product product);
    void removeAllProducts(Product product);
    HashMap<Product, Integer> countProducts();
    void clearCart();
    ArrayList<Product> getAllProducts();
    Double GetCartTotal();
}
