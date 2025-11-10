package com.vromanyu.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vromanyu.ws.entity.User;
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

        User user = new User("Viktor", "Romanyuk", "viktor.rmn9@gmail.com");
        User savedUser = new User("Viktor", "Romanyuk", "viktor.rmn9@gmail.com");
        savedUser.setId(1);

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(savedUser);

        ResultActions result = mockMvc.perform(post("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Viktor"))
                .andExpect(jsonPath("$.lastname").value("Romanyuk"));
    }

    @Test
    public void shouldFindUser() throws Exception {

        User user = new User("Viktor", "Romanyuk", "viktor.rmn9@gmail.com");
        user.setId(1);

        Mockito.when(userService.findUserById(Mockito.anyInt())).thenReturn(user);

        ResultActions result = mockMvc.perform(get("/api/users/1").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Viktor"))
                .andExpect(jsonPath("$.lastname").value("Romanyuk"));
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {

        Mockito.when(userService.findAllUsers()).thenReturn(List.of(
                new User("Viktor", "Romanyuk", "vik@gmail.com"),
                new User("Viktor", "Romanyuk", "vikt@gmail.com")
        ));

        ResultActions result = mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        User user = new User("Viktor", "Romanyuk", "viktor.rmn9@gmail.com");
        user.setId(1);

        Mockito.when(userService.updateUser(Mockito.any(User.class))).thenReturn(user);

        ResultActions result = mockMvc.perform(put("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

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


}
