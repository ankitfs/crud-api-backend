package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.entity.EmployeeEntity;
import com.ankit.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceTest {

    //which service we want to test
    @InjectMocks
    private EmployeeService employeeService;

    //declare the dependencies

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_student_entity_when_student_dto_given() throws Exception{
        //Given
        var employeeDTO = new CreateEmployeeRequestDTO(
                            "rajeev masand",
                            "rajeev@bollywoodrocks.com");

        Optional<EmployeeEntity> existsEmployeeEntity = Optional.of(new EmployeeEntity(
                                1,
                                "rajeev masand",
                                "rajeev@bollywoodrocks.com",
                                Timestamp.valueOf(LocalDateTime.now()),
                                Timestamp.valueOf(LocalDateTime.now())));

        EmployeeEntity employeeEntity = new EmployeeEntity(
                                1,
                                "rajeev masand",
                                "rajeev@bollywoodrocks.com",
                                Timestamp.valueOf(LocalDateTime.now()),
                                Timestamp.valueOf(LocalDateTime.now()));

        var employeeResponseDTO = new EmployeeResponseDTO(
                                "rajeev masand",
                                "rajeev@bollywoodrocks.com",
                                Timestamp.valueOf(LocalDateTime.now()));

        Mockito.when(employeeRepository.findByEmail(employeeDTO.getEmail())).thenReturn(existsEmployeeEntity);
        Mockito.when(employeeMapper.dtoToEmployeeEntity(employeeDTO)).thenReturn(employeeEntity);
        Mockito.when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);
        Mockito.when(employeeMapper.entityToEmployeeDTO(employeeEntity)).thenReturn(employeeResponseDTO);
//        Mockito.when(employeeService.createEmployee(employeeDTO)).thenReturn(employeeResponseDTO);

        //When
        EmployeeResponseDTO employeeResponseDTO2 = employeeService.createEmployee(employeeDTO);
        //Then
        assertEquals(employeeResponseDTO2.getName(), employeeDTO.getName());
    }
}