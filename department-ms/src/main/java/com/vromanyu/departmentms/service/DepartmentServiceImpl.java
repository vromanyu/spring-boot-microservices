package com.vromanyu.departmentms.service;

import com.vromanyu.departmentms.dto.DepartmentRequestDto;
import com.vromanyu.departmentms.dto.DepartmentResponseDto;
import com.vromanyu.departmentms.entity.Department;
import com.vromanyu.departmentms.repository.DepartmentRepository;
import com.vromanyu.mapper.DepartmentMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto save(DepartmentRequestDto departmentRequestDto) {
        Department department = DepartmentMapper.toDepartment(departmentRequestDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDepartmentResponseDto(savedDepartment);
    }

    @Override
    public DepartmentResponseDto getById(Integer id) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("department with id " + id + " not found"));
        return DepartmentMapper.toDepartmentResponseDto(department);
    }
}
