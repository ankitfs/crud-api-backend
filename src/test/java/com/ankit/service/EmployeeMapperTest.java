package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.entity.EmployeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperTest {

    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    @DisplayName("This test returns Employee Entity when DTO was provided")
    public void should_returnEntity_when_DTO_Provided() throws Exception{

        //Given
        CreateEmployeeRequestDTO createEmployeeDTO =
                new CreateEmployeeRequestDTO("ankit", "agarwal@gmail.com");

        //When
        EmployeeEntity employeeEntity = employeeMapper.dtoToEmployeeEntity(createEmployeeDTO);

        //Then
        assertEquals(createEmployeeDTO.getName(), employeeEntity.getName());
        assertEquals(createEmployeeDTO.getEmail(), employeeEntity.getEmail());
        assertNull(employeeEntity.getCreatedAt());
    }

    @Test
    @DisplayName("This test will throw NullPointer Exception when DTO is null")
    public void should_throw_npe_when_dto_is_null() {
        //given
        CreateEmployeeRequestDTO createEmployeeRequestDTO = null;

        //when
        Mockito.when(employeeMapper.dtoToEmployeeEntity(createEmployeeRequestDTO)).thenThrow(new NullPointerException("Employee DTO cannot be null"));

        //then
        Exception exp = assertThrows(NullPointerException.class, () -> employeeMapper.dtoToEmployeeEntity(createEmployeeRequestDTO));

        assertEquals("Employee DTO cannot be null", exp.getMessage());
    }


    @Test
    @DisplayName("This test returns Employee DTO when entity is provided")
    public void should_returnDTO_when_Entity_Provided() throws Exception {

        //given
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "ankit", "ankit@gmail.com", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        //when
        EmployeeResponseDTO createEmployeeDTO = employeeMapper.entityToEmployeeDTO(employeeEntity);

        //then
        assertNotNull(employeeEntity);
        assertNotNull(createEmployeeDTO);
        assertEquals(employeeEntity.getName(), createEmployeeDTO.getName());
        assertEquals(employeeEntity.getEmail(), createEmployeeDTO.getEmail());
    }
}