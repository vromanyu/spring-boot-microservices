package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-MS")
public interface DepartmentMsClient {

    @GetMapping(value = "api/departments/{id}", produces = "application/json")
    DepartmentDto getById(@PathVariable Integer id);
}
