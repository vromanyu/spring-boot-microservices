package com.vromanyu.employeems.dto;

public record EmployeeResponseDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        Boolean emailVerified
) {
}
