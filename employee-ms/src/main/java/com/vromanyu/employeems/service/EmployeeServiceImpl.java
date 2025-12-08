package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.DepartmentDto;
import com.vromanyu.employeems.dto.EmployeeRequestDto;
import com.vromanyu.employeems.dto.EmployeeResponseDto;
import com.vromanyu.employeems.dto.EmployeeResponseWithDepartmentDto;
import com.vromanyu.employeems.entity.Employee;
import com.vromanyu.employeems.mapper.EmployeeMapper;
import com.vromanyu.employeems.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;

    @Override
    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        Employee employee = EmployeeMapper.toEmployee(employeeRequestDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeResponseDto(savedEmployee);
    }

    @Override
    public EmployeeResponseWithDepartmentDto getById(Integer id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("employee with id " + id + " not found"));

        ResponseEntity<@NonNull DepartmentDto> response = restTemplate.getForEntity("http://localhost:8181/api/departments/" + employee.getDepartmentId(), DepartmentDto.class);
        DepartmentDto departmentDto = response.getBody();
        EmployeeResponseDto employeeResponseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return new EmployeeResponseWithDepartmentDto(employeeResponseDto, departmentDto);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().map(EmployeeMapper::toEmployeeResponseDto).toList();
    }
}
