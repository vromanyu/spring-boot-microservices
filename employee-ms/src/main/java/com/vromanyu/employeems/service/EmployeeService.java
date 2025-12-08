package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getById(Integer id);
    List<EmployeeResponseDto> getAll();
}
