package com.vromanyu.employee_management_ms.dto;

import com.vromanyu.employee_management_ms.entity.Employee;

public record EmployeeDto(long id, String firstName, String lastName, String email, DepartmentDto department) {

    public static final class EmployeeMapper {

        public static EmployeeDto toDto(Employee employee) {
            return new EmployeeDto(employee.getId(),
                    employee.getFirstName(),
                    employee.getFirstName(),
                    employee.getEmail(),
                    DepartmentDto.DepartmentMapper.toDto(employee.getDepartment()));
        }

        public static Employee toEntity(EmployeeDto employeeDto) {
            Employee employee = new Employee();
            employee.setId(employeeDto.id());
            employee.setFirstName(employeeDto.firstName());
            employee.setLastName(employeeDto.lastName());
            employee.setEmail(employeeDto.email());
            employee.setDepartment(DepartmentDto.DepartmentMapper.fromDto(employeeDto.department()));
            return employee;
        }
    }
}
