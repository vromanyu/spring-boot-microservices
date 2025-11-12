package com.vromanyu.employee_management_ms.controller;

import com.vromanyu.employee_management_ms.dto.DepartmentDto;
import com.vromanyu.employee_management_ms.dto.DepartmentWithEmployeesDto;
import com.vromanyu.employee_management_ms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = departmentService.addDepartment(departmentDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDepartmentDto.id()).toUri();
        return ResponseEntity.created(location).body(savedDepartmentDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentWithEmployeesDto> getDepartmentById(@PathVariable long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentWithEmployeesDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable long id, @RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentDto = departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.ok(savedDepartmentDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
