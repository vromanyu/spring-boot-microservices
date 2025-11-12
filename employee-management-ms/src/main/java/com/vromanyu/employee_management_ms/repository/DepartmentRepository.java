package com.vromanyu.employee_management_ms.repository;

import com.vromanyu.employee_management_ms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}