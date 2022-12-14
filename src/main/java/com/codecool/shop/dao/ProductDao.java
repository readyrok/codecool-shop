package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(String id);
    void remove(String id);

    List<Product> getAll();
    List<Product> getBy(Supplier supplier);

    List<Product> getBy(String supplier);

    List<Product> getBy(ProductCategory productCategory);

}
