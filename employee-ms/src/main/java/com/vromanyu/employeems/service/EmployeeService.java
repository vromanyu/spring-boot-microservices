package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;
import com.vromanyu.employeems.dto.EmployeeResponseWithDepartmentDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseWithDepartmentDto getById(Integer id);
    List<EmployeeResponseDto> getAll();
    void verifyEmail(String uuid);
}
