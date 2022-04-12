package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ErrorLogging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT name FROM category WHERE id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }

            String name = rs.getString(1);
            ProductCategory category = new ProductCategory(name, "Hardware", "");
            category.setId(id);

            return category;
        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT id, name FROM category";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            List<ProductCategory> categories = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);

                ProductCategory category = new ProductCategory(name, "Hardware", "");
                category.setId(id);

                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }
}
