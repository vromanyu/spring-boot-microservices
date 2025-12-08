package com.vromanyu.employeems.repository;

import com.vromanyu.employeems.entity.Employee;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<@NonNull Employee, @NonNull Integer> {
}
