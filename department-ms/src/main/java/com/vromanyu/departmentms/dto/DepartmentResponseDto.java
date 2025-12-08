package com.vromanyu.departmentms.dto;

public record DepartmentResponseDto(
        Integer id,
        String name,
        String description,
        String code
) {
}
