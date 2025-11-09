package com.vromanyu.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vromanyu.ws.entity.User;
import com.vromanyu.ws.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureTestDatabase
class SpringbootRestfulWebservicesApplicationUserServiceTests {

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

        ResultActions result = mockMvc.perform(post("/api/users/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)));

        result.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Viktor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Romanyuk"));
    }


}
