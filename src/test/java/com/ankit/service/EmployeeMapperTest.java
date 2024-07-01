package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.entity.EmployeeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    @Test
    @DisplayName("This test returns Employee Entity when DTO was provided")
    public void should_retunEntity_when_DTO_Provided() throws Exception{

        //Given
        CreateEmployeeRequestDTO createEmployeeDTO =
                new CreateEmployeeRequestDTO("ankit", "agarwal@gmail.com");

        //When
        EmployeeEntity employeeEntity = EmployeeMapper.dtoToEmployeeEntity(createEmployeeDTO);

        //Then
        assertEquals(createEmployeeDTO.getName(), employeeEntity.getName());
        assertEquals(createEmployeeDTO.getEmail(), employeeEntity.getEmail());
        assertNull(employeeEntity.getCreatedAt());
    }


    @Test
    @DisplayName("This test returns Employee DTO when entity is provided")
    public void should_returnDTO_when_Entity_Provided() throws Exception {

        //given
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "ankit", "ankit@gmail.com", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        //when
        CreateEmployeeRequestDTO createEmployeeDTO = EmployeeMapper.entityToEmployeeDTO(employeeEntity);

        //then
        assertNotNull(employeeEntity);
        assertNotNull(createEmployeeDTO);
        assertEquals(employeeEntity.getName(), createEmployeeDTO.getName());
        assertEquals(employeeEntity.getEmail(), createEmployeeDTO.getEmail());
    }
}