package com.vromanyu.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vromanyu.ws.dto.UserDto;
import com.vromanyu.ws.exception.ResourceNotFound;
import com.vromanyu.ws.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class SpringbootRestfulWebservicesApplicationUserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateUser() throws Exception {

        UserDto savedUser = new UserDto(1,"Viktor", "Romanyuk", "viktor.rmn9@gmail.com");

        Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(savedUser);

        ResultActions result = mockMvc.perform(post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedUser)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Viktor"))
                .andExpect(jsonPath("$.lastname").value("Romanyuk"));
    }

    @Test
    public void shouldFindUser() throws Exception {

        UserDto userDto = new UserDto(1, "Viktor", "Romanyuk", "viktor.rmn9@gmail.com");

        Mockito.when(userService.findUserById(Mockito.anyInt())).thenReturn(userDto);

        ResultActions result = mockMvc.perform(get("/api/users/1").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Viktor"))
                .andExpect(jsonPath("$.lastname").value("Romanyuk"));
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {

        Mockito.when(userService.findAllUsers()).thenReturn(List.of(
                new UserDto(1, "Viktor", "Romanyuk", "vik@gmail.com"),
                new UserDto(2, "Viktor", "Romanyuk", "vikt@gmail.com")
        ));

        ResultActions result = mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        UserDto userDto = new UserDto(1, "Viktor", "Romanyuk", "viktor.rmn9@gmail.com");

        Mockito.when(userService.updateUser(Mockito.any(UserDto.class))).thenReturn(userDto);

        ResultActions result = mockMvc.perform(put("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Viktor"))
                .andExpect(jsonPath("$.lastname").value("Romanyuk"));
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        Mockito.doNothing().when(userService).deleteUser(Mockito.anyInt());

        ResultActions result = mockMvc.perform(delete("/api/users/{id}", 1));

        result.andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnErrorDetails() throws Exception {

        Mockito.when(userService.findUserById(Mockito.anyInt())).thenThrow(new ResourceNotFound("User", "id", 1));

        ResultActions result = mockMvc.perform(get("/api/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.path").exists())
                .andExpect(jsonPath("$.errorCode").exists());
    }

    @Test
    public void shouldThrowWhenUpdateWithoutId() throws Exception {

        Mockito.when(userService.updateUser(Mockito.any(UserDto.class))).thenThrow(new IllegalArgumentException("id can't be null"));

        UserDto user = new UserDto(null,"Viktor", "Romanyuk", "viktor@gmail.com");

        ResultActions result = mockMvc.perform(put("/api/users").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.path").exists())
                .andExpect(jsonPath("$.errorCode").exists());
    }

    @Test
    public void shouldThrowGenericException() throws Exception {

        Mockito.doThrow(Exception.class).when(userService);

        ResultActions result = mockMvc.perform(get("/api/users")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.path").exists())
                .andExpect(jsonPath("$.errorCode").exists());

    }


}
