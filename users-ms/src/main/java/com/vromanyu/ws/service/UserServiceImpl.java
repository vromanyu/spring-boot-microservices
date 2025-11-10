package com.vromanyu.ws.service;

import com.vromanyu.ws.dto.UserDto;
import com.vromanyu.ws.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        return UserDto.UserMapper.toUserDto(userRepository.save(UserDto.UserMapper.toUser(userDto)));
    }

    @Override
    public UserDto findUserById(int id) {
        return UserDto.UserMapper.toUserDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserDto.UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return UserDto.UserMapper.toUserDto(userRepository.save(UserDto.UserMapper.toUser(userDto)));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
