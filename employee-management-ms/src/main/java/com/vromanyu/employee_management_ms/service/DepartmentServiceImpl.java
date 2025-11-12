package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.DepartmentDto;
import com.vromanyu.employee_management_ms.dto.DepartmentWithEmployeesDto;
import com.vromanyu.employee_management_ms.dto.EmployeeDto;
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
    public DepartmentWithEmployeesDto getDepartmentById(long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
        List<EmployeeDto> employees = department.getEmployees().stream().map(EmployeeDto.EmployeeMapper::toDto).toList();
        return new DepartmentWithEmployeesDto(department.getId(), department.getDepartmentName(), department.getDepartmentDescription(), employees);
    }

    @Override
    public List<DepartmentWithEmployeesDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> {
            List<EmployeeDto> employees = department.getEmployees().stream().map(EmployeeDto.EmployeeMapper::toDto).toList();
            return new DepartmentWithEmployeesDto(
                    department.getId(),
                    department.getDepartmentName(),
                    department.getDepartmentDescription(),
                    employees
            );
        }).toList();
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
