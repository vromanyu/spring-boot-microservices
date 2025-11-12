package com.vromanyu.employee_management_ms.controller;

import com.vromanyu.employee_management_ms.dto.EmployeeDto;
import com.vromanyu.employee_management_ms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/{departmentId}/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable Long departmentId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.addEmployee(departmentId, employeeDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.id()).toUri();
        return ResponseEntity.created(location).body(savedEmployee);
    }
}
