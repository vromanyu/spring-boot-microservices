package com.vromanyu.ws.service;

import com.vromanyu.ws.entity.User;


public interface UserService {
    User createUser(User user);
    User findUserById(int id);

}
