package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {
    void addOrder(Order order);
    List<Order> getOrderByUserId(String userId);
}
