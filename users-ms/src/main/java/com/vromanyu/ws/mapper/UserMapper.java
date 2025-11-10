package com.vromanyu.ws.mapper;

import com.vromanyu.ws.dto.UserDto;
import com.vromanyu.ws.entity.User;

public final class UserMapper {

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.id());
        user.setFirstname(userDto.firstName());
        user.setLastname(userDto.lastName());
        user.setEmail(userDto.email());
        return user;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }
}
