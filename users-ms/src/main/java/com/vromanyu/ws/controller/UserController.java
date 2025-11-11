package com.vromanyu.ws.controller;

import com.vromanyu.ws.dto.UserDto;
import com.vromanyu.ws.entity.User;
import com.vromanyu.ws.service.UserRequestLogService;
import com.vromanyu.ws.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    private final UserRequestLogService userRequestLogService;

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
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {
        userRequestLogService.logRequest(request);
        UserDto savedUser = userService.createUser(userDto);
        return ResponseEntity.
                created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.id()).toUri())
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
    public ResponseEntity<UserDto> findUserById(@PathVariable int id, HttpServletRequest request) {
        userRequestLogService.logRequest(request);
        UserDto foundUser = userService.findUserById(id);
        return ResponseEntity.ok(foundUser);
    }

    @Operation(
            tags = {"users"},
            method = "findAllUsers",
            operationId = "findAllUsers"
    )
    @GetMapping(value = "/users", produces =   MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> findAllUsers(HttpServletRequest request){
        userRequestLogService.logRequest(request);
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
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request) {
        userRequestLogService.logRequest(request);
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
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
    public ResponseEntity<Void> deleteUser(@PathVariable int id, HttpServletRequest request) {
        userRequestLogService.logRequest(request);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
