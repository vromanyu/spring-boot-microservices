package com.vromanyu.departmentms.repository;

import com.vromanyu.departmentms.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
