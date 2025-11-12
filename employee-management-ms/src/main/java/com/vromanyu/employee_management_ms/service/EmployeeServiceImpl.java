package com.vromanyu.employee_management_ms.service;

import com.vromanyu.employee_management_ms.dto.EmployeeDto;
import com.vromanyu.employee_management_ms.entity.Department;
import com.vromanyu.employee_management_ms.entity.Employee;
import com.vromanyu.employee_management_ms.repository.DepartmentRepository;
import com.vromanyu.employee_management_ms.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public EmployeeDto getEmployeeById(long departmentId, long id) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("department not found"));
        Employee employee = department.getEmployees().stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("employee not found"));
        return EmployeeDto.EmployeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByDeparment(long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("department not found"));
        return department.getEmployees().stream().map(EmployeeDto.EmployeeMapper::toDto).collect(Collectors.toList());
    }

}
