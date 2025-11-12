package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.DepartmentDto;
import com.vromanyu.employee_management_ms.dto.DepartmentWithEmployeesDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentWithEmployeesDto getDepartmentById(long id);
    List<DepartmentWithEmployeesDto> getAllDepartments();
    DepartmentDto updateDepartment(long id, DepartmentDto departmentDto);
    void deleteDepartment(long id);
}
