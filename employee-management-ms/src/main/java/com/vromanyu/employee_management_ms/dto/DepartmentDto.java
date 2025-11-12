package com.vromanyu.employee_management_ms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vromanyu.employee_management_ms.entity.Department;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDto(Long id, String departmentName, String departmentDescription, List<EmployeeDto> employees) {

    public static final class DepartmentMapper {

        public static DepartmentDto toDto(Department department) {
            return new DepartmentDto(department.getId(),
                    department.getDepartmentName(),
                    department.getDepartmentDescription(),
                    null);
        }

        public static Department fromDto(DepartmentDto dto) {
            Department department = new Department();
            department.setId(dto.id());
            department.setDepartmentName(dto.departmentName());
            department.setDepartmentDescription(dto.departmentDescription());
            if (dto.employees() != null) {
                department.setEmployees(dto.employees().stream().map(EmployeeDto.EmployeeMapper::toEntity).toList());
            }
            return department;
        }

    }
}