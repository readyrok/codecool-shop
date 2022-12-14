package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(String categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(String categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }


    public List<Product> getEveryProducts(){
        return productDao.getAll();
    }
    public List<ProductCategory> getEveryProductCategory(){
        return productCategoryDao.getAll();
    }

    public List<Product> getSupplierDao(String supplier) {
        return productDao.getBy(supplier);
    }

}
