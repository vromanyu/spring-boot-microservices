package com.vromanyu.employeems.mapper;

import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;
import com.vromanyu.employeems.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {}

    public static Employee toEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDto.firstName());
        employee.setLastName(employeeRequestDto.lastName());
        employee.setEmail(employeeRequestDto.email());
        return employee;
    }

    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee) {
        return new EmployeeResponseDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail());
    }
}
