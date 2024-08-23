package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.dto.ListEmployeesResponseDTO;
import com.ankit.entity.EmployeeEntity;
import com.ankit.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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


    @Test
    @DisplayName("This test will return list of all employees")
    public void should_display_list_of_employees() throws Exception{
        //Given
        //List of 5 employees entities
        EmployeeEntity employee1 = new EmployeeEntity(1,"Rahul Yadav", "rahul.yadav@gmail.com", Timestamp.valueOf(LocalDateTime.now()), null);
        EmployeeEntity employee2 = new EmployeeEntity(2, "Rakesh Chawla", "rakesh.chawla@rediffmail.com", Timestamp.valueOf(LocalDateTime.now()), null);
        EmployeeEntity employee3 = new EmployeeEntity(3, "Nirmala Tomar", "nirmala.tomar@yahoo.com", Timestamp.valueOf(LocalDateTime.now()), null);
        EmployeeEntity employee4 = new EmployeeEntity(4, "Sahil Gupta", "sahil.gupta@rediffmail.com", Timestamp.valueOf(LocalDateTime.now()), null);
        EmployeeEntity employee5 = new EmployeeEntity(5, "Reena Yadav", "reena.yadav@gmail.com", Timestamp.valueOf(LocalDateTime.now()), null);

        List<EmployeeEntity> employeeEntityList = Arrays.asList(employee1, employee2, employee3, employee4, employee5);
        ListEmployeesResponseDTO listEmployeesEntities = new ListEmployeesResponseDTO();

        //Mock the calls
        when(employeeRepository.findAll()).thenReturn(employeeEntityList);
        when(employeeMapper.entityToEmployeeDTO(any(EmployeeEntity.class))).thenReturn(new EmployeeResponseDTO("ankit", "agaarwal", Timestamp.valueOf(LocalDateTime.now())));

        //When
        ListEmployeesResponseDTO listEmployeesEntitiesResult = employeeService.listAllEmployees();

        //Then
        assertNotNull(listEmployeesEntitiesResult);
        assertEquals(listEmployeesEntitiesResult.getNoOfEmployees(), employeeEntityList.size());

    }

    @Test
    public void should_find_employee_when_employeeid_provided() throws Exception{
        //Given
        EmployeeEntity employee5 = new EmployeeEntity(5,
                                                    "Reena Yadav",
                                                    "reena.yadav@gmail.com",
                                                        Timestamp.valueOf(LocalDateTime.now()),
                                                        null);

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO("Reena Yadav",
                                                                            "reena.yadav@gmail.com",
                                                                                Timestamp.valueOf(LocalDateTime.now()));


        //Mock the Calls
        when(employeeRepository.findByEmail(employee5.getEmail())).thenReturn(Optional.of(employee5));
        when(employeeMapper.entityToEmployeeDTO(employee5)).thenReturn(employeeResponseDTO);

        //When
        EmployeeResponseDTO employeeResponse = employeeService.findEmployeeById(employee5.getEmail());

        //Then
        assertEquals(employee5.getEmail(), employeeResponse.getEmail());
    }

    @Test
    @DisplayName("This test will update an employee")
    public void should_update_employee_when_dto_provided() throws Exception {
        //Given
        //Employee DTO
        CreateEmployeeRequestDTO employeeRequestDTO = new CreateEmployeeRequestDTO("ankit kumar agarwal", "ankit@gmail.com");

        EmployeeEntity existingEmployee = new EmployeeEntity(1,
                                                            "ankit agarwal",
                                                            "ankit@gmail.com",
                                                            Timestamp.valueOf(LocalDateTime.now()),
                                                            null);

        EmployeeEntity updatedEmployeeEntity = new EmployeeEntity(1,
                                                            "ankit kumar agarwal",
                                                            "ankit@gmail.com",
                                                                Timestamp.valueOf(LocalDateTime.now()),
                                                    null);

        EmployeeEntity finalUpdatedEmployeeEntity = new EmployeeEntity(1,
                                                                "ankit kumar agarwal",
                                                                "ankit@gmail.com",
                                                                Timestamp.valueOf(LocalDateTime.now()),
                                                                null);

        EmployeeResponseDTO employeeResponse = new EmployeeResponseDTO("ankit kumar agarwal",
                                                                        "ankit@gmail.com",
                                                                            Timestamp.valueOf(LocalDateTime.now()));

        //Mock the Calls
        when(employeeRepository.findByEmail(employeeRequestDTO.getEmail())).thenReturn(Optional.of(existingEmployee));
        when(employeeMapper.dtoToEmployeeEntity(employeeRequestDTO)).thenReturn(existingEmployee);
        when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployeeEntity);
        when(employeeService.updateEmployee(employeeRequestDTO)).thenReturn(employeeResponse);

        //When
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeRequestDTO);


        //Then
        assertEquals(employeeRequestDTO.getName(), employeeResponseDTO.getName());
    }
}