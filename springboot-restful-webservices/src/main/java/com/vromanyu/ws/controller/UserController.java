package com.vromanyu.ws.controller;

import com.vromanyu.ws.entity.User;
import com.vromanyu.ws.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(User user){
        User savedUser = userService.createUser(user);
        return ResponseEntity.
                created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri())
                .body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id){
        User foundUser = userService.findUserById(id);
        return ResponseEntity.ok(foundUser);
    }

}
