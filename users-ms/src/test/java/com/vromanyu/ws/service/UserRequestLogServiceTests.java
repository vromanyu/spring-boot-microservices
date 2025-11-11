package com.vromanyu.ws.service;

import com.vromanyu.ws.dto.UserRequestLogDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
public class UserRequestLogServiceTests {

    @Autowired
    private UserRequestLogService userRequestLogService;

    @Test
    @DirtiesContext
    public void shouldWriteLog() throws Exception{
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setServerName("localhost");
        mockHttpServletRequest.setServerPort(8080);
        mockHttpServletRequest.setScheme("http");
        mockHttpServletRequest.setRequestURI("/test");
        mockHttpServletRequest.setMethod("GET");
        mockHttpServletRequest.setContentType("application/json");

        userRequestLogService.logRequest(mockHttpServletRequest);

        Thread.sleep(1000);

        List<UserRequestLogDto> userRequestLogDtos = userRequestLogService.getAllRequests();
        Assertions.assertFalse(userRequestLogDtos.isEmpty());
    }
}
