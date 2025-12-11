package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.DepartmentDto;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMsClientFallback implements DepartmentMsClient {

    @Override
    public DepartmentDto getById(Integer id) {
        return new DepartmentDto(0, "fallback department", "fallback department", "999");
    }
}
