package com.vromanyu.ws.service;

import com.vromanyu.ws.dto.UserDto;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto findUserById(int id);
    List<UserDto> findAllUsers();
    UserDto updateUser(UserDto userDto);
    void deleteUser(int id);
}
