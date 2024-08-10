package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.entity.EmployeeEntity;
import com.ankit.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    //which service we want to test
    @InjectMocks
    private EmployeeService employeeService;

    //declare the dependencies

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;


    @Test
    @DisplayName("This test will save student entity when student dto is provided")
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

        when(employeeRepository.findByEmail(employeeDTO.getEmail())).thenReturn(existsEmployeeEntity);
        when(employeeMapper.dtoToEmployeeEntity(employeeDTO)).thenReturn(employeeEntity);
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);
        when(employeeMapper.entityToEmployeeDTO(employeeEntity)).thenReturn(employeeResponseDTO);

        //When
        EmployeeResponseDTO employeeResponseDTO2 = employeeService.createEmployee(employeeDTO);
        //Then
        assertEquals(employeeResponseDTO2.getName(), employeeDTO.getName());
    }

    @Test
    @DisplayName("This Test will throw NLP Exception when employee DTO is null")
    public void should_throw_exception_when_student_dto_not_given() {

        //given
        CreateEmployeeRequestDTO employeeDTO = null;

        //when
        when(employeeMapper.dtoToEmployeeEntity(employeeDTO)).thenThrow(new NullPointerException("Employee DTO cannot be null"));

        //then
        Exception exception = assertThrows(NullPointerException.class, () -> employeeMapper.dtoToEmployeeEntity(employeeDTO));

        //var exp = Assertions.assertThrows(NullPointerException.class, () -> employeeMapper.dtoToEmployeeEntity(createEmployeeRequestDTO));

        assertEquals("Employee DTO cannot be null", exception.getMessage());
    }
}