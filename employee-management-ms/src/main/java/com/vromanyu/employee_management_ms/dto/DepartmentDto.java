package com.vromanyu.employee_management_ms.dto;

import com.vromanyu.employee_management_ms.entity.Department;


public record DepartmentDto(Long id, String departmentName, String departmentDescription) {

    public static final class DepartmentMapper {

        public static DepartmentDto toDto(Department department) {
            return new DepartmentDto(department.getId(), department.getDepartmentName(), department.getDepartmentDescription());
        }

        public static Department fromDto(DepartmentDto dto) {
            Department department = new Department();
            department.setId(dto.id());
            department.setDepartmentName(dto.departmentName());
            department.setDepartmentDescription(dto.departmentDescription());
            return department;
        }

    }
}