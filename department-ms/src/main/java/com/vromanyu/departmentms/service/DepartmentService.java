package com.vromanyu.departmentms.service;

import com.vromanyu.departmentms.dto.DepartmentRequestDto;
import com.vromanyu.departmentms.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto save(DepartmentRequestDto departmentRequestDto);
    DepartmentResponseDto getById(Integer id);
    List<DepartmentResponseDto> getAll();
}
