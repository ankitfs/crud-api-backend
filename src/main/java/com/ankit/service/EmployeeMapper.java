package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO entityToEmployeeDTO(EmployeeEntity entity) {
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setName(entity.getName());
        employeeResponseDTO.setEmail(entity.getEmail());
        employeeResponseDTO.setCreatedDate(entity.getCreatedAt());

        return employeeResponseDTO;
    }

    public EmployeeEntity dtoToEmployeeEntity(CreateEmployeeRequestDTO employeeDTO, EmployeeEntity callerEntity) {
        if(employeeDTO == null) {
            throw new NullPointerException("Employee DTO cannot be null");
        }
        EmployeeEntity entity =  callerEntity == null ? new EmployeeEntity() : callerEntity;
        entity.setName(employeeDTO.getName());
        entity.setEmail(employeeDTO.getEmail());
        return entity;
    }
}
