package com.ankit.service;

import java.util.List;
import java.util.Optional;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.dto.ListEmployeesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.entity.EmployeeEntity;
import com.ankit.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO employeeDTO) throws Exception{

		EmployeeEntity employeeEntity = EmployeeMapper.dtoToEmployeeEntity(employeeDTO);

		Optional<EmployeeEntity> employeeExists = empRepository.findByEmail(employeeDTO.getEmail());

		if(!employeeExists.isPresent()) {
			employeeEntity = empRepository.save(employeeEntity);
		}


		return EmployeeMapper.entityToEmployeeDTO(employeeEntity);
	}
	
	public ListEmployeesResponseDTO listAllEmployees() throws Exception{
		ListEmployeesResponseDTO employeesResponse = new ListEmployeesResponseDTO();
		List<EmployeeEntity> employeeEntityList = empRepository.findAll();
		employeesResponse.setNoOfEmployees(employeeEntityList.size());
		List<EmployeeResponseDTO> employeesDTOList = employeeEntityList.stream().
							map(employeeEntity -> {
								EmployeeResponseDTO employeeResponseDTO = null;
								try {
									employeeResponseDTO = EmployeeMapper.entityToEmployeeDTO(employeeEntity);
								}
								catch (Exception ex) {
									throw new RuntimeException(ex.getMessage());
								}
								return employeeResponseDTO;
							}).toList();
		employeesResponse.setEmployeesList(employeesDTOList);

		return employeesResponse;
	}
	
	public EmployeeResponseDTO findEmployeeById(String email) throws Exception{
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		Optional<EmployeeEntity> employeeEntity = empRepository.findByEmail(email);
		if(employeeEntity.isPresent()) {
			responseDTO = EmployeeMapper.entityToEmployeeDTO(employeeEntity.get());
		}

		return responseDTO;
	}
	
	public EmployeeResponseDTO updateEmployee(CreateEmployeeRequestDTO employeeRequestDTO) throws Exception{
		EmployeeEntity updatedEmployee = null;
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
		Optional<EmployeeEntity> existingEmployee = empRepository.findByEmail(employeeRequestDTO.getEmail());
		if(existingEmployee.isPresent()) {
			updatedEmployee = EmployeeMapper.dtoToEmployeeEntity(employeeRequestDTO);
			updatedEmployee.setId(existingEmployee.get().getId());

			updatedEmployee = empRepository.save(updatedEmployee);

			employeeResponseDTO = EmployeeMapper.entityToEmployeeDTO(updatedEmployee);
		}
		return employeeResponseDTO;
	}
	
	public void deleteEmployee(String email) throws Exception{
		empRepository.deleteByEmail(email);
	}
}
