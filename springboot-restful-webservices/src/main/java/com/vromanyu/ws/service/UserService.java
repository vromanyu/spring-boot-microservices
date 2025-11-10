package com.vromanyu.ws.service;

import com.vromanyu.ws.entity.User;

import java.util.List;


public interface UserService {
    User createUser(User user);
    User findUserById(int id);
    List<User> findAllUsers();
}
