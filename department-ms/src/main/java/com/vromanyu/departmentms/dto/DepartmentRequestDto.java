package com.vromanyu.departmentms.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartmentRequestDto(
        @NotBlank(message = "name can't be blank")
        String name,

        @NotBlank(message = "description can't be blank")
        String description,

        @NotBlank(message = "code can't be blank")
        String code
) {
}
