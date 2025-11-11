package com.vromanyu.ws.service;

import com.vromanyu.ws.dto.UserRequestLogDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserRequestLogService{
    void logRequest(HttpServletRequest request);
    List<UserRequestLogDto> getAllRequests();
}
