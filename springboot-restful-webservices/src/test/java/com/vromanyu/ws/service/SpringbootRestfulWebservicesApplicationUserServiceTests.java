package com.vromanyu.ws.service;

import com.vromanyu.ws.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestDatabase
class SpringbootRestfulWebservicesApplicationUserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void shouldSaveUser(){
        User user = new User("Viktor", "Romanyuk", "viktor@gmail.com");

        User savedUser = userService.createUser(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
    }

    @Test
    public void shouldFindUserById(){
        User user = new User("Viktor", "Romanyuk", "viktor@gmail.com");

        User savedUser = userService.createUser(user);

        User foundUser = userService.findUserById(savedUser.getId());

        Assertions.assertNotNull(foundUser);
        Assertions.assertNotNull(foundUser.getId());
    }

    @Test
    public void shouldThrowWhenUserNotFound(){
        User user = new User("Viktor", "Romanyuk", "viktor@gmail.com");

        userService.createUser(user);

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.findUserById(0));

    }
}
