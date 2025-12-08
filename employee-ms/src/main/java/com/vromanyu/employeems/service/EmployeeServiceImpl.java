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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentMsClient departmentMsClient;

    @Override
    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        if (!isValidDepartment(employeeRequestDto)) {
            throw new RuntimeException("invalid department id");
        }
        Employee employee = EmployeeMapper.toEmployee(employeeRequestDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeResponseDto(savedEmployee);
    }

    private boolean isValidDepartment(EmployeeRequestDto employeeRequestDto) {
        DepartmentDto departmentDto = departmentMsClient.getById(Integer.valueOf(employeeRequestDto.departmentId()));
        return departmentDto != null;
    }

    @Override
    public EmployeeResponseWithDepartmentDto getById(Integer id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("employee with id " + id + " not found"));

        DepartmentDto departmentDto = departmentMsClient.getById(Integer.valueOf(employee.getDepartmentId()));
        EmployeeResponseDto employeeResponseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return new EmployeeResponseWithDepartmentDto(employeeResponseDto, departmentDto);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().map(EmployeeMapper::toEmployeeResponseDto).toList();
    }

}
