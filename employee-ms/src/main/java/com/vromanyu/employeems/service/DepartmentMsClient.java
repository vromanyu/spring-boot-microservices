package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8181/api/departments", name = "department-ms")
public interface DepartmentMsClient {

    @GetMapping(value = "/{id}", produces = "application/json")
    DepartmentDto getById(@PathVariable(name = "id") Integer id);
}
