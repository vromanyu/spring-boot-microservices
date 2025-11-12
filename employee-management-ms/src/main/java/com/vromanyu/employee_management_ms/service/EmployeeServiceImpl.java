package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.EmployeeDto;
import com.vromanyu.employee_management_ms.entity.Department;
import com.vromanyu.employee_management_ms.entity.Employee;
import com.vromanyu.employee_management_ms.repository.DepartmentRepository;
import com.vromanyu.employee_management_ms.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto addEmployee(long departmentId, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("department not found"));
        Employee employee = EmployeeDto.EmployeeMapper.toEntity(employeeDto);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeDto.EmployeeMapper.toDto(savedEmployee);
    }

}
