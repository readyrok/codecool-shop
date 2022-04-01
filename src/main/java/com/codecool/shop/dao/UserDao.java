package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    void addUser(User user);
    void addRegisteredUser(User user);
    User getUser(String userId);
    User getRegisteredUser(String userId);
    User getUserByEmailPass(String email,String pass);
}
