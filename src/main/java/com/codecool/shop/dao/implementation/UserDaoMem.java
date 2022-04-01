package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMem implements UserDao {
    private List<User> users = new ArrayList<>();
    private List<User> registeredUsers = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem(){
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addRegisteredUser(User user) {
        registeredUsers.add(user);
    }

    @Override
    public User getUser(String userId) {
        return users.stream().filter(t -> t.getId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public User getRegisteredUser(String userId) {
        return registeredUsers.stream().filter(t -> t.getId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public User getUserByEmailPass(String email, String pass) {
        return registeredUsers.stream().filter(t -> t.getEmail().equals(email) && t.getPassword().equals(pass)).findFirst().orElse(null);
    }

}
