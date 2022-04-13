package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.*;
import com.codecool.shop.service.ProductService;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    ProductDao productDao;
    SupplierDao supplierDao;
    ProductCategoryDao productCategoryDao;
    ShoppingCartDao cartDao;
    UserDao userDao;
    OrderDao orderDao;
    DaoImplementation implementation = DaoImplementation.DATABASE;

    public void setup(DaoImplementation implementation) throws SQLException {
        DataSource dataSource = connect();
        ProductService service = new ProductService(implementation, dataSource);
        productDao = service.getProductDao();
        supplierDao = service.getSupplierDao();
        productCategoryDao = service.getProductCategoryDao();
        cartDao = service.getCartDao();
        userDao = service.getUserDao();
        orderDao = service.getOrderDao();
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ApplicationProperties properties = new ApplicationProperties();

        dataSource.setDatabaseName(properties.readProperty("DB_NAME"));
        dataSource.setUser(properties.readProperty("USER"));
        dataSource.setPassword(properties.readProperty("PASSWORD"));

        System.out.println("Trying to connect...");
        dataSource.getConnection();
        System.out.println("Connection OK");

        return dataSource;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public ShoppingCartDao getCartDao() {
        return cartDao;
    }

    public UserDao getCustomerDao() {
        return userDao;
    }

    public DaoImplementation getImplementation() {
        return implementation;
    }
}
