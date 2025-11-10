package com.vromanyu.ws.service;

import com.vromanyu.ws.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestDatabase
class SpringbootRestfulWebservicesApplicationUserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    @DirtiesContext
    public void shouldSaveUser(){
        User user = new User("Viktor", "Romanyuk", "viktor@gmail.com");

        User savedUser = userService.createUser(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
    }

    @Test
    @DirtiesContext
    public void shouldFindUserById(){
        User user = new User("Viktor", "Romanyuk", "viktors@gmail.com");

        User savedUser = userService.createUser(user);

        User foundUser = userService.findUserById(savedUser.getId());

        Assertions.assertNotNull(foundUser);
        Assertions.assertNotNull(foundUser.getId());
    }

    @Test
    @DirtiesContext
    public void shouldThrowWhenUserNotFound(){
        User user = new User("Viktor", "Romanyuk", "viktorea@gmail.com");

        userService.createUser(user);

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.findUserById(0));

    }

    @Test
    @DirtiesContext
    public void shouldReturnAllUsers(){
        List<User> users = List.of(
                new User("Viktor", "Romanyuk", "viktorcd2.r@gmail.com"),
                new User("Viktor", "Romanyuk", "viaask@gmail.com")
        );

        users.forEach(userService::createUser);

        List<User> allSavedUsers = userService.findAllUsers();

        Assertions.assertNotNull(allSavedUsers);
        Assertions.assertFalse(allSavedUsers.isEmpty());
        Assertions.assertEquals(users.size(), allSavedUsers.size());
    }

    @Test
    @DirtiesContext
    public void shouldUpdateUser(){
        User user = new User("Viktor", "Romanyuk", "viktoraa@gmail.com");
        User savedUser = userService.createUser(user);
        savedUser.setFirstname("Antonio");
        savedUser.setLastname("Romanyak");
        User updatedUser = userService.updateUser(savedUser);
        List<User> allSavedUsers = userService.findAllUsers();

        Assertions.assertNotNull(updatedUser.getId());
        Assertions.assertEquals("Antonio", updatedUser.getFirstname());
        Assertions.assertEquals("Romanyak", updatedUser.getLastname());
        Assertions.assertEquals(1, allSavedUsers.size());
    }
}
