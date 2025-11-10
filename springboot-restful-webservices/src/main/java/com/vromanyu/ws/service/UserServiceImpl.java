package com.vromanyu.ws.service;

import com.vromanyu.ws.entity.User;
import com.vromanyu.ws.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
