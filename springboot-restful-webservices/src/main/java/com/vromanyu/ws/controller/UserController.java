package com.vromanyu.ws.controller;

import com.vromanyu.ws.entity.User;
import com.vromanyu.ws.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Operation(
            tags = {"users"},
            method = "createUser",
            operationId = "createUser",
            requestBody =
                    @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description = "user",
                            required = true,
                            content = {
                                    @Content(schema = @Schema(implementation = User.class))
                            }
                    )


    )
    @PostMapping(value = "/users", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(User user){
        User savedUser = userService.createUser(user);
        return ResponseEntity.
                created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri())
                .body(savedUser);
    }


    @Operation(
            tags = {"users"},
            method = "findUserById",
            operationId = "findUserById",
            parameters = {
                    @Parameter(name = "id", required = true)
            }

    )
    @GetMapping(value = "/users/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserById(@PathVariable int id){
        User foundUser = userService.findUserById(id);
        return ResponseEntity.ok(foundUser);
    }

    @Operation(
            tags = {"users"},
            method = "findAllUsers",
            operationId = "findAllUsers"
    )
    @GetMapping(value = "/users", produces =   MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }



    @Operation(
            tags = {"users"},
            method = "updateUser",
            operationId = "updateUser",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "user",
                    required = true,
                    content = {
                            @Content(schema = @Schema(implementation = User.class))
                    }
            )


    )
    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User savedUser = userService.updateUser(user);
        return ResponseEntity.ok(savedUser);
    }


    @Operation(
            tags = {"users"},
            method = "deleteUser",
            operationId = "deleteUser",
            parameters = {
                    @Parameter(name = "id", required = true)
            }

    )
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
