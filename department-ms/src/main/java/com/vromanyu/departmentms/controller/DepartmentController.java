package com.vromanyu.departmentms.controller;

import com.vromanyu.departmentms.dto.DepartmentRequestDto;
import com.vromanyu.departmentms.dto.DepartmentResponseDto;
import com.vromanyu.departmentms.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<@NonNull DepartmentResponseDto> save (@RequestBody @Valid DepartmentRequestDto departmentRequestDto) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        DepartmentResponseDto savedDepartment = departmentService.save(departmentRequestDto);
        URI savedDepartmentLocation = builder.path("/departments/{id}").build(savedDepartment.id());
        return ResponseEntity.created(savedDepartmentLocation).body(savedDepartment);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<@NonNull DepartmentResponseDto> getById(@PathVariable(name = "id") Integer id) {
        DepartmentResponseDto savedDepartment = departmentService.getById(id);
        return ResponseEntity.ok(savedDepartment);
    }

}
