package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.database.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.database.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.database.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.memory.*;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private ShoppingCartDao shoppingCartDao;
    private SupplierDao supplierDao;
    private UserDao userDao = UserDaoMem.getInstance();
    private OrderDao orderDao = OrderDaoMem.getInstance();
    private DaoImplementation daoImplementation;

    public ProductService() {
    }

    public ProductService(DaoImplementation implementation, DataSource dataSource) {
        this.daoImplementation = implementation;
        switch(implementation) {
            case MEMORY:
                this.productDao = ProductDaoMem.getInstance();
                this.productCategoryDao = ProductCategoryDaoMem.getInstance();
                this.shoppingCartDao = ShoppingCartDaoMem.getInstance();
                this.supplierDao = SupplierDaoMem.getInstance();
                break;
            case DATABASE:
                this.productDao = new ProductDaoJdbc(dataSource);
                this.productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
                this.supplierDao = new SupplierDaoJdbc(dataSource);
                this.shoppingCartDao = ShoppingCartDaoMem.getInstance();
                break;
        }

    }

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, ShoppingCartDao shoppingCartDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.shoppingCartDao = shoppingCartDao;
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getEveryProducts(){
        return productDao.getAll();
    }

    public ShoppingCart getShoppingCartByUserId(String userID) {
        return shoppingCartDao.find(userID);
    }

    public List<ProductCategory> getAllCategory() {
        return productCategoryDao.getAll();
    }

    public List<Product> getSupplier(String supplier) {
        return productDao.getBy(supplier);
    }

    public SupplierDao getSupplierDao() { return supplierDao; }

    public Product getProductById(int productId) {
        return productDao.find(productId);
    }

    public User getUserByEmailPass(String email, String pass) {
        return userDao.getUserByEmailPass(email, pass);
    }

    public void addProductToCart(String userId, int productId) {
        Product product = getProductById(productId);
        ShoppingCart shoppingCart;
        if (shoppingCartDao.getAll().containsKey(userId)) {
            shoppingCart = shoppingCartDao.find(userId);
            shoppingCart.addProduct(product);
        } else {
            shoppingCart = new ShoppingCart(product);
        }
        shoppingCartDao.addShoppingCart(userId, shoppingCart);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void addRegisteredUser(User user) {
        userDao.addRegisteredUser(user);
    }

    public User getUserById(String userId) {
        return userDao.getUser(userId);
    }
    public User getRegisteredUserById(String userId) {
        return userDao.getRegisteredUser(userId);
    }


    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    public List<Order> getOrderByUserId(String userId) {
        return orderDao.getOrderByUserId(userId);
    }

    public void removeProductFromCart(String userId, int productId) {
        Product product = getProductById(productId);
        ShoppingCart shoppingCart;
        shoppingCart = shoppingCartDao.find(userId);
        shoppingCart.removeLineItem(productId);
        shoppingCartDao.addShoppingCart(userId, shoppingCart);
    }

    public void decreaseProduct(String userId, int productId) {
        ShoppingCart shoppingCart;
        shoppingCart = shoppingCartDao.find(userId);
        shoppingCart.decreaseLineItem(productId);
        shoppingCartDao.addShoppingCart(userId, shoppingCart);
    }

    public void increaseProduct(String userId, int productId) {
        ShoppingCart shoppingCart;
        shoppingCart = shoppingCartDao.find(userId);
        shoppingCart.increaseLineItem(productId);
        shoppingCartDao.addShoppingCart(userId, shoppingCart);
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public ShoppingCartDao getCartDao() {
        return shoppingCartDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}