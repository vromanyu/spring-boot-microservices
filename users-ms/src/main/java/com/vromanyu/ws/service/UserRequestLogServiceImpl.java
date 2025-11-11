package com.vromanyu.ws.service;

import com.vromanyu.ws.dto.UserRequestLogDto;
import com.vromanyu.ws.repository.UserRequestLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserRequestLogServiceImpl implements  UserRequestLogService{

    private final UserRequestLogRepository userRequestLogRepository;

    @Async
    public void logRequest(HttpServletRequest request) {
        UserRequestLogDto userRequestLogDto = new UserRequestLogDto(
                request.getServerName() + ":" + request.getServerPort(),
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getQueryString(),
                request.getRemoteAddr()
        );
        userRequestLogRepository.save(UserRequestLogDto.UserRequestLogMapper.toUserRequestLog(userRequestLogDto));
    }
}
