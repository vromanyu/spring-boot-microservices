package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-MS", fallback = DepartmentMsClient.DepartmentMsClientFallback.class)
public interface DepartmentMsClient {

    @GetMapping(value = "api/departments/{id}", produces = "application/json")
    DepartmentDto getById(@PathVariable Integer id);

    @Component
    class DepartmentMsClientFallback implements DepartmentMsClient {

        @Override
        public DepartmentDto getById(Integer id) {
            return new DepartmentDto(0,
                    "fallback department",
                    "a fallback department in case the actaul department couldn't be fetched",
                    "000"
            );
        }
    }
}
