package com.vromanyu.ws.service;

import com.vromanyu.ws.dto.UserDto;
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
        UserDto userDto = new UserDto(null, "Viktor", "Romanyuk", "viktor@gmail.com");

        UserDto savedUser = userService.createUser(userDto);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.id());
    }

    @Test
    @DirtiesContext
    public void shouldFindUserById(){
        UserDto userDto = new UserDto(null, "Viktor", "Romanyuk", "viktors@gmail.com");

        UserDto savedUser = userService.createUser(userDto);

        UserDto foundUser = userService.findUserById(savedUser.id());

        Assertions.assertNotNull(foundUser);
        Assertions.assertNotNull(foundUser.id());
    }

    @Test
    @DirtiesContext
    public void shouldThrowWhenUserNotFound(){
        UserDto userDto = new UserDto(null, "Viktor", "Romanyuk", "viktorea@gmail.com");

        userService.createUser(userDto);

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.findUserById(0));

    }

    @Test
    @DirtiesContext
    public void shouldReturnAllUsers(){
        List<UserDto> users = List.of(
                new UserDto(null, "Viktor", "Romanyuk", "viktorcd2.r@gmail.com"),
                new UserDto(null, "Viktor", "Romanyuk", "viaask@gmail.com")
        );

        users.forEach(userService::createUser);

        List<UserDto> allSavedUsers = userService.findAllUsers();

        Assertions.assertNotNull(allSavedUsers);
        Assertions.assertFalse(allSavedUsers.isEmpty());
        Assertions.assertEquals(users.size(), allSavedUsers.size());
    }

    @Test
    @DirtiesContext
    public void shouldUpdateUser(){
        UserDto userDto = new UserDto(null, "Viktor", "Romanyuk", "viktoraa@gmail.com");
        UserDto savedUser = userService.createUser(userDto);
        UserDto updateUser = new UserDto(savedUser.id(), "Antonio","Romanayk","viktoraa@gmail.com");
        updateUser = userService.updateUser(updateUser);
        List<UserDto> allSavedUsers = userService.findAllUsers();

        Assertions.assertNotNull(updateUser.id());
        Assertions.assertEquals("Antonio", updateUser.firstName());
        Assertions.assertEquals("Romanayk", updateUser.lastName());
        Assertions.assertEquals(1, allSavedUsers.size());
    }

    @Test
    @DirtiesContext
    public void shouldDeleteUser(){
        UserDto userDto = new UserDto(null, "Viktor", "Romanyuk", "vik@gmail.com");
        UserDto savedUser = userService.createUser(userDto);

        userService.deleteUser(savedUser.id());

        List<UserDto> savedUsers = userService.findAllUsers();

        Assertions.assertNotNull(savedUsers);
        Assertions.assertTrue(savedUsers.isEmpty());
    }
}
