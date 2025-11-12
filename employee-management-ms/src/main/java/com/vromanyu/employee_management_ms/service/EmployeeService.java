package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto addEmployee(long departmentId, EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(long departmentId, long id);
    List<EmployeeDto> getAllEmployeesByDeparment(long departmentId);
}
