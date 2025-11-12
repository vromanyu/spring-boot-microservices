package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.DepartmentDto;
import com.vromanyu.employee_management_ms.entity.Department;
import com.vromanyu.employee_management_ms.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department savedDepartment = departmentRepository.save(DepartmentDto.DepartmentMapper.fromDto(departmentDto));
        return DepartmentDto.DepartmentMapper.toDto(savedDepartment);
    }
}
