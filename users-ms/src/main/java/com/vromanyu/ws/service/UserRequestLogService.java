package com.vromanyu.ws.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserRequestLogService{
    void logRequest(HttpServletRequest request);
}
