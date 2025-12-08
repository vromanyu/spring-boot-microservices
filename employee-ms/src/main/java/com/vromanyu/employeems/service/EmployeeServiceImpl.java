package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;
import com.vromanyu.employeems.entity.Employee;
import com.vromanyu.employeems.mapper.EmployeeMapper;
import com.vromanyu.employeems.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        Employee employee = EmployeeMapper.toEmployee(employeeRequestDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeResponseDto(savedEmployee);
    }

    @Override
    public EmployeeResponseDto getById(Integer id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("employee with id " + id + " not found"));
        return EmployeeMapper.toEmployeeResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().map(EmployeeMapper::toEmployeeResponseDto).toList();
    }
}
