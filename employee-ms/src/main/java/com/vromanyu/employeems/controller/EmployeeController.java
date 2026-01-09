package com.vromanyu.employeems.controller;

import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;
import com.vromanyu.employeems.dto.EmployeeResponseWithDepartmentDto;
import com.vromanyu.employeems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger =  LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<@NonNull EmployeeResponseDto> save (@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        logger.info("{} POST /api/employees (save) saving {}", this.getClass().getSimpleName(), employeeRequestDto);
        EmployeeResponseDto savedEmployee = employeeService.save(employeeRequestDto);
        URI savedEmployeeLocation = builder.path("/{id}").build(savedEmployee.id());
        return ResponseEntity.created(savedEmployeeLocation).body(savedEmployee);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<@NonNull EmployeeResponseWithDepartmentDto> getById(@PathVariable Integer id) {
        EmployeeResponseWithDepartmentDto savedEmployee = employeeService.getById(id);
        logger.info("{} GET /api/employees (getById) returning {}", this.getClass().getSimpleName(), savedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<@NonNull List<EmployeeResponseDto>> getAll() {
        List<EmployeeResponseDto> savedEmployees = employeeService.getAll();
        logger.info("{} GET /api/employees (getAll) returning {}", this.getClass().getSimpleName(), savedEmployees);
        return ResponseEntity.ok(savedEmployees);
    }

}
