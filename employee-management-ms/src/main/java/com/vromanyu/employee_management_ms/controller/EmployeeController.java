package com.vromanyu.employee_management_ms.controller;

import com.vromanyu.employee_management_ms.dto.EmployeeDto;
import com.vromanyu.employee_management_ms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping(value = "/{departmentId}/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long departmentId, @PathVariable long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(departmentId, id));
    }

    @GetMapping(value = "/{departmentId}/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@PathVariable Long departmentId) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDeparment(departmentId));
    }

    @PutMapping(value = "/{departmentId}/employee/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long departmentId, @PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.updateEmployee(departmentId, id, employeeDto);
        return ResponseEntity.ok(savedEmployee);
    }

    @DeleteMapping(value = "/{departmentId}/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long departmentId, @PathVariable long id) {
        employeeService.deleteEmployee(departmentId, id);
        return ResponseEntity.noContent().build();
    }

}
