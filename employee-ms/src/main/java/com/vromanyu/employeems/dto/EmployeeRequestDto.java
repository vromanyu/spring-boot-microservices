package com.vromanyu.employeems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRequestDto(

        @NotBlank(message = "firstName can't be blank")
        String firstName,

        @NotBlank(message = "lastName can't be blank")
        String lastName,

        @NotBlank(message = "email can't be blank")
        @Email(message = "invalid email format")
        String email,

        @NotBlank(message = "departmentId can't be blank")
        Integer departmentId
) {
}
