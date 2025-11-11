package com.vromanyu.ws.repository;

import com.vromanyu.ws.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldGenerateIdForNewUser(){
        User user = new User("Viktor", "Romanyuk", "viktor.rmn@gmail.com");

        User savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser.getId());
    }

    @Test
    public void shouldCreateIdsForAllUsers(){
        List<User> users = List.of(
                new User("Viktor", "Romanyuk", "v@gmail.com"),
                new User("Viktor", "Romanyuk", "vi@gmail.com"),
                new User("Viktor", "Romanyuk", "vik@gmail.com"),
                new User("Viktor", "Romanyuk", "vikt@gmail.com"),
                new User("Viktor", "Romanyuk", "viktor@gmail.com")
        );

        List<User> savedUsers = userRepository.saveAll(users);

        savedUsers.stream().map(User::getId).forEach(Assertions::assertNotNull);
    }

    @Test
    public void shouldFindUsersById(){
        List<User> users = List.of(
                new User("Viktor", "Romanyuk", "v@gmail.com"),
                new User("Viktor", "Romanyuk", "vi@gmail.com"),
                new User("Viktor", "Romanyuk", "vik@gmail.com"),
                new User("Viktor", "Romanyuk", "vikt@gmail.com"),
                new User("Viktor", "Romanyuk", "viktor@gmail.com")
        );

        List<User> savedUsers = userRepository.saveAll(users);

        savedUsers.forEach(user -> Assertions.assertTrue(userRepository.findById(user.getId()).isPresent()));
    }

    @Test
    public void shouldThrowWhenNotUniqueEmail(){
        List<User> users = List.of(new User("Viktor", "Romanyuk", "viktor@gmail.com"), new User("Viktor", "Romanyuk", "viktor@gmail.com"));

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAll(users));
    }

    @Test
    public void shouldFindUserByEmail(){
        User user = new User("Viktor", "Romanyuk", "viktor@gmail.com");

        userRepository.save(user);

        Optional<User> userByEmail = userRepository.findByEmail("viktor@gmail.com");

        Assertions.assertTrue(userByEmail.isPresent());
        Assertions.assertAll(() -> Assertions.assertNotNull(userByEmail.get().getId()),
                () -> Assertions.assertEquals(user.getFirstname(), userByEmail.get().getFirstname()),
                () -> Assertions.assertEquals(user.getLastname(), userByEmail.get().getLastname()),
                () -> Assertions.assertEquals(user.getEmail(), userByEmail.get().getEmail()));
    }

}
