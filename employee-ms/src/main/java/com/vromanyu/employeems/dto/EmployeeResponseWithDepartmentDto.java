package com.vromanyu.employeems.dto;

public record EmployeeResponseWithDepartmentDto(
        EmployeeResponseDto employee,
        DepartmentDto department
) {
}
