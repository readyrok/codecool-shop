package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ErrorLogging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT product.name, price, currency, description, category.name, supplier.name FROM product" +
                    " LEFT JOIN category ON product.category_id = category.id" +
                    " LEFT JOIN supplier ON product.supplier_id = supplier.id WHERE product.id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String productName = rs.getString(1);
            float price = rs.getFloat(2);
            String currency = rs.getString(3);
            String description = rs.getString(4);
            String categoryName = rs.getString(5);
            String supplierName = rs.getString(6);
            ProductCategory category = new ProductCategory(categoryName, "Hardware", "");
            Supplier supplier = new Supplier(supplierName, "");
            Product result = new Product(productName, price, currency, description, category, supplier);
            result.setId(id);
            return result;

        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection con = dataSource.getConnection()){
            String query = "SELECT product.id, product.name, description, price, currency, category.name, supplier.name FROM product" +
                    " LEFT JOIN category ON product.category_id = category.id" +
                    " LEFT JOIN supplier ON product.supplier_id = supplier.id";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            List<Product> results = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                float price = rs.getFloat(4);
                String currency = rs.getString(5);
                String category = rs.getString(6);
                String supplierName = rs.getString(7);
                ProductCategory productCategory = new ProductCategory(category, "Hardware", "");
                Supplier supplier = new Supplier(supplierName, "");

                Product product = new Product(name, price, currency, description, productCategory, supplier);
                product.setId(id);

                results.add(product);
            }
            return results;
        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String supplierName = supplier.getName();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT product.id, product.name, description, price, currency, category.name, supplier.name FROM product " +
                    " LEFT JOIN category ON product.category_id = category.id" +
                    " LEFT JOIN supplier ON product.supplier_id = supplier.id" +
                    " WHERE supplier.name = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, supplierName);
            ResultSet rs = st.executeQuery();

            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String productName = rs.getString(2);
                String description = rs.getString(3);
                float price = rs.getFloat(4);
                String currency = rs.getString(5);
                String categoryName = rs.getString(6);
                String suppName = rs.getString(7);
                ProductCategory productCategory = new ProductCategory(categoryName, "Hardware", "");
                Supplier supplier1 = new Supplier(suppName, "");

                Product product = new Product(productName, price, currency, description, productCategory, supplier1);
                product.setId(id);

                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String categoryName = productCategory.getName();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT product.id, product.name, product.description, " +
                    "product.price, product.currency, category.name, supplier.name FROM product" +
                    " LEFT JOIN category ON product.category_id = category.id" +
                    " LEFT JOIN supplier ON product.supplier_id = supplier.id" +
                    " WHERE category.name = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, categoryName);
            ResultSet rs = st.executeQuery();

            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                float price = rs.getFloat(4);
                String currency = rs.getString(5);
                String catName = rs.getString(6);
                String supName = rs.getString(7);
                ProductCategory category = new ProductCategory(catName, "Hardware", "");
                Supplier supplier = new Supplier(supName, "");

                Product product = new Product(name, price, currency, description, category, supplier);
                product.setId(id);

                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(String supplier) {
        return null;
    }
}
