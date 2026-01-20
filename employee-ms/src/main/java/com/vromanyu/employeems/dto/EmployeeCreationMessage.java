package com.vromanyu.employeems.dto;


import org.jspecify.annotations.NonNull;

public record EmployeeCreationMessage(@NonNull String uuid, @NonNull String firstName, @NonNull String lastName, @NonNull String email) {
}
