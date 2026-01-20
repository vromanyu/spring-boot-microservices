package com.vromanyu.employeems.service;

import com.vromanyu.employeems.dto.*;
import com.vromanyu.employeems.entity.Employee;
import com.vromanyu.employeems.exception.EmailAlreadyVerifiedException;
import com.vromanyu.employeems.exception.EmployeeUuidNotFoundException;
import com.vromanyu.employeems.mapper.EmployeeMapper;
import com.vromanyu.employeems.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentMsClient departmentMsClient;
    private final StreamBridge streamBridge;
    private static final Logger logger =  LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeResponseDto save(EmployeeRequestDto employeeRequestDto) {
        if (!isValidDepartment(employeeRequestDto)) {
            throw new RuntimeException("invalid department id");
        }
        Employee employee = EmployeeMapper.toEmployee(employeeRequestDto);
        Employee savedEmployee = employeeRepository.save(employee);
        streamBridge.send("sendEmail-out-0", new EmployeeCreationMessage(savedEmployee.getUuid(), savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getEmail()));
        return EmployeeMapper.toEmployeeResponseDto(savedEmployee);
    }

    private boolean isValidDepartment(EmployeeRequestDto employeeRequestDto) {
        logger.info("{} isValidDepartment({}), start department-ms/departmentMsClients.getById({})", this.getClass().getSimpleName(), employeeRequestDto, employeeRequestDto.departmentId());
        DepartmentDto departmentDto = departmentMsClient.getById(employeeRequestDto.departmentId());
        logger.info("{} isValidDepartment({}), finish department-ms/departmentMsClients.getById({})", this.getClass().getSimpleName(), employeeRequestDto, employeeRequestDto.departmentId());
        return departmentDto != null;
    }

    @Override
    public EmployeeResponseWithDepartmentDto getById(Integer id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("employee with id " + id + " not found"));

        logger.info("{} getById({}), start department-ms/departmentMsClients.getById({})", this.getClass().getSimpleName(), id, employee.getDepartmentId());
        DepartmentDto departmentDto = departmentMsClient.getById(employee.getDepartmentId());
        logger.info("{} getById({}), finish department-ms/departmentMsClients.getById({})", this.getClass().getSimpleName(), id, employee.getDepartmentId());
        EmployeeResponseDto employeeResponseDto = EmployeeMapper.toEmployeeResponseDto(employee);
        return new EmployeeResponseWithDepartmentDto(employeeResponseDto, departmentDto);
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().map(EmployeeMapper::toEmployeeResponseDto).toList();
    }

    @Override
    public void verifyEmail(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid).orElseThrow(() -> new EmployeeUuidNotFoundException("employee with uuid " + uuid + " not found"));
        if (employee.getEmailVerified().equals(Boolean.TRUE))
            throw new EmailAlreadyVerifiedException("email is already verified");
        employee.setEmailVerified(Boolean.TRUE);
        employeeRepository.save(employee);
    }

}
