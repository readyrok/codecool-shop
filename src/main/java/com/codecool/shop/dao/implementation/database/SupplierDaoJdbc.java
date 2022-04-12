package com.codecool.shop.dao.implementation.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ErrorLogging;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT name FROM supplier WHERE id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString(1);
            Supplier result = new Supplier(name, "");
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
    public List<Supplier> getAll() {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT id, name FROM supplier";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            List<Supplier> suppliers = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);

                Supplier supplier = new Supplier(name, "");
                supplier.setId(id);

                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException e) {
            ErrorLogging.log(e);
            throw new RuntimeException(e);
        }
    }
}
