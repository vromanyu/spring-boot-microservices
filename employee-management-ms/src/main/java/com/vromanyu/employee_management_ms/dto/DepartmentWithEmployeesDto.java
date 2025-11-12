package com.vromanyu.employee_management_ms.dto;

import java.util.List;

public record DepartmentWithEmployeesDto(Long departmentId, String departmentName, String departmentDescription, List<EmployeeDto> employees) {
}
