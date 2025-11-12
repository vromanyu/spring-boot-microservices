package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(long id);
    List<DepartmentDto> getAllDepartments();
}
