package com.ankit.service;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.dto.ListEmployeesResponseDTO;
import com.ankit.entity.EmployeeEntity;
import com.ankit.exception.ApplicationException;
import com.ankit.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private EmployeeMapper employeeMapper;

	private Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO employeeDTO) {

		logger.info("Entered CreateEmployee Service");

		//checking whether request dto email already exists. If found, throw an exception with appropriate message
		empRepository.findByEmail(employeeDTO.getEmail()).
				ifPresent(action -> {
					logger.error("Employee Already Exists having email : {}", employeeDTO.getEmail());
					throw new ApplicationException("Employee Already Exists");
				});

		EmployeeEntity employeeEntity = employeeMapper.dtoToEmployeeEntity(employeeDTO, null);
		employeeEntity.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

		employeeEntity = empRepository.save(employeeEntity);

		return employeeMapper.entityToEmployeeDTO(employeeEntity);
	}
	
	public ListEmployeesResponseDTO listAllEmployees() {
		ListEmployeesResponseDTO employeesResponse = new ListEmployeesResponseDTO();
		List<EmployeeEntity> employeeEntityList = empRepository.findAll();
		employeesResponse.setNoOfEmployees(employeeEntityList.size());
		List<EmployeeResponseDTO> employeesDTOList = employeeEntityList.stream().map(employeeMapper::entityToEmployeeDTO).toList();
		employeesResponse.setEmployeesList(employeesDTOList);

		return employeesResponse;
	}
	
	public EmployeeResponseDTO findEmployeeById(String email) {
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		EmployeeEntity employeeEntity = empRepository.findByEmail(email).orElseThrow(() -> {
			logger.error("Employee didn't found with email: {}", email);
			throw new ApplicationException("Employee Didn't found with email: "+ email);
		});

		responseDTO = employeeMapper.entityToEmployeeDTO(employeeEntity);

		return responseDTO;
	}
	
	public EmployeeResponseDTO updateEmployee(CreateEmployeeRequestDTO employeeRequestDTO) {

		EmployeeEntity existingEmployee = empRepository.findByEmail(employeeRequestDTO.getEmail()).orElseThrow(()
											-> new NullPointerException("Employee Not Found having email :" +
												employeeRequestDTO.getEmail()));

		existingEmployee = employeeMapper.dtoToEmployeeEntity(employeeRequestDTO, existingEmployee);
		existingEmployee.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

		EmployeeEntity updatedEmployee = empRepository.save(existingEmployee);

        return employeeMapper.entityToEmployeeDTO(updatedEmployee);
	}
	
	public void deleteEmployee(String email) {
		EmployeeEntity existingEmployee = empRepository.findByEmail(email).
				orElseThrow(() ->
				new NullPointerException("Employee Not Found having email:" + email));

		empRepository.delete(existingEmployee);
	}
}
