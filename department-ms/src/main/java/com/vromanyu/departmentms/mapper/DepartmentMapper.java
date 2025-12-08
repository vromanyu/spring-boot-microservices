package com.vromanyu.departmentms.mapper;

import com.vromanyu.departmentms.dto.DepartmentRequestDto;
import com.vromanyu.departmentms.dto.DepartmentResponseDto;
import com.vromanyu.departmentms.entity.Department;

public class DepartmentMapper {

    private DepartmentMapper() {}

    public static Department toDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setName(departmentRequestDto.name());
        department.setDescription(departmentRequestDto.description());
        department.setCode(departmentRequestDto.code());
        return department;
    }

    public static DepartmentResponseDto toDepartmentResponseDto(Department department) {
        return new DepartmentResponseDto(department.getId(),
                department.getName(),
                department.getDescription(),
                department.getCode());
    }
}
