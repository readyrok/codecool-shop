package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private List<Order> orders = new ArrayList<>();
    private static OrderDaoMem instance = null;

    private OrderDaoMem(){
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }
    @Override
    public void addOrder(Order order){
        orders.add(order);
    }

    @Override
    public List<Order> getOrderByUserId(String userId){
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(orders.stream().filter(t -> t.getUser().getId().equals(userId)).findFirst().orElse(null));
        return userOrders;
    }
}
