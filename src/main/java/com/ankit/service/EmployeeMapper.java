package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.entity.EmployeeEntity;

public class EmployeeMapper {

    public static EmployeeResponseDTO entityToEmployeeDTO(EmployeeEntity entity) throws Exception{
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setName(entity.getName());
        employeeResponseDTO.setEmail(entity.getEmail());
        employeeResponseDTO.setCreatedDate(entity.getCreatedAt());

        return employeeResponseDTO;
    }

    public static EmployeeEntity dtoToEmployeeEntity(CreateEmployeeRequestDTO employeeDTO) throws Exception{
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(employeeDTO.getName());
        entity.setEmail(employeeDTO.getEmail());
        return entity;
    }
}
