package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.DepartmentDto;
import com.vromanyu.employee_management_ms.entity.Department;
import com.vromanyu.employee_management_ms.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department savedDepartment = departmentRepository.save(DepartmentDto.DepartmentMapper.fromDto(departmentDto));
        return DepartmentDto.DepartmentMapper.toDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
        return DepartmentDto.DepartmentMapper.toDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream().map(DepartmentDto.DepartmentMapper::toDto).toList();
    }

    @Override
    public DepartmentDto updateDepartment(long id, DepartmentDto departmentDto) {
        Department savedDepartment =  departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
        savedDepartment.setDepartmentName(departmentDto.departmentName());
        savedDepartment.setDepartmentDescription(departmentDto.departmentDescription());
        Department updatedDepartment = departmentRepository.save(savedDepartment);
        return DepartmentDto.DepartmentMapper.toDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        Department savedDepartment = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
        departmentRepository.delete(savedDepartment);
    }

}
