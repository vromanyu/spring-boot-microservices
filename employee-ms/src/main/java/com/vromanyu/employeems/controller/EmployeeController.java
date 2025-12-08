package com.vromanyu.employeems.controller;

import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;
import com.vromanyu.employeems.dto.EmployeeResponseWithDepartmentDto;
import com.vromanyu.employeems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<@NonNull EmployeeResponseDto> save (@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        EmployeeResponseDto savedEmployee = employeeService.save(employeeRequestDto);
        URI savedEmployeeLocation = builder.path("/{id}").build(savedEmployee.id());
        return ResponseEntity.created(savedEmployeeLocation).body(savedEmployee);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<@NonNull EmployeeResponseWithDepartmentDto> getById(@PathVariable(name = "id") Integer id) {
        EmployeeResponseWithDepartmentDto savedEmployee = employeeService.getById(id);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<@NonNull List<EmployeeResponseDto>> getAll() {
        List<EmployeeResponseDto> savedEmployees = employeeService.getAll();
        return ResponseEntity.ok(savedEmployees);
    }

}
