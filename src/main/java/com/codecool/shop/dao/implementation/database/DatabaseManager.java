package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {
    ProductDao productDao;
    SupplierDao supplierDao;
    ProductCategoryDao productCategoryDao;
    ShoppingCartDao cartDao;
    UserDao userDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
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
}
