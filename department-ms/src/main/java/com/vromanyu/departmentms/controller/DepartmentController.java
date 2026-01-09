package com.vromanyu.departmentms.controller;

import com.vromanyu.departmentms.dto.DepartmentRequestDto;
import com.vromanyu.departmentms.dto.DepartmentResponseDto;
import com.vromanyu.departmentms.service.DepartmentService;
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
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private static final Logger logger =  LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<@NonNull DepartmentResponseDto> save (@RequestBody @Valid DepartmentRequestDto departmentRequestDto) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        logger.info("{} POST /api/departments (save) saving {}", this.getClass().getSimpleName(), departmentRequestDto);
        DepartmentResponseDto savedDepartment = departmentService.save(departmentRequestDto);
        URI savedDepartmentLocation = builder.path("/departments/{id}").build(savedDepartment.id());
        return ResponseEntity.created(savedDepartmentLocation).body(savedDepartment);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<@NonNull DepartmentResponseDto> getById(@PathVariable(name = "id") Integer id) {
        DepartmentResponseDto savedDepartment = departmentService.getById(id);
        logger.info("{} GET /api/departments (getById) returning {}", this.getClass().getSimpleName(), savedDepartment);
        return ResponseEntity.ok(savedDepartment);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<@NonNull List<DepartmentResponseDto>> getAll() {
        List<DepartmentResponseDto> savedDepartments = departmentService.getAll();
        logger.info("{} GET /api/departments (getAll) returning {}", this.getClass().getSimpleName(), savedDepartments);
        return ResponseEntity.ok(savedDepartments);
    }

}
