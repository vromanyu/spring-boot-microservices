package com.vromanyu.employeems.repository;

import com.vromanyu.employeems.entity.Employee;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<@NonNull Employee, @NonNull Integer> {

    Optional<Employee> findByUuid(String uuid);
}
