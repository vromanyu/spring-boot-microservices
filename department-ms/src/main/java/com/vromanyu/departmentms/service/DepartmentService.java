package com.vromanyu.departmentms.service;

import com.vromanyu.departmentms.dto.DepartmentRequestDto;
import com.vromanyu.departmentms.dto.DepartmentResponseDto;

public interface DepartmentService {
    DepartmentResponseDto save(DepartmentRequestDto departmentRequestDto);

}
