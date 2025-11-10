package com.vromanyu.ws.dto;


import com.vromanyu.ws.entity.UserRequestLog;

public record UserRequestLogDto(
        String server, String requestUrl, String method, String requestParameters
) {

    public static final class UserRequestLogMapper{
        public static UserRequestLog toUserRequestLog(UserRequestLogDto userRequestLogDto) {
            return new UserRequestLog(userRequestLogDto.server, userRequestLogDto.requestUrl, userRequestLogDto.method(), userRequestLogDto.requestParameters);
        }
    }
}
