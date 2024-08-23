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

	@Autowired
	private EmployeeMapper employeeMapper;
	
	public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO employeeDTO) throws Exception{

		EmployeeEntity employeeEntity = employeeMapper.dtoToEmployeeEntity(employeeDTO);

		Optional<EmployeeEntity> employeeExists = empRepository.findByEmail(employeeDTO.getEmail());

		if(!employeeExists.isEmpty()) {
			employeeEntity = empRepository.save(employeeEntity);
		}

		return employeeMapper.entityToEmployeeDTO(employeeEntity);
	}
	
	public ListEmployeesResponseDTO listAllEmployees() throws Exception{
		ListEmployeesResponseDTO employeesResponse = new ListEmployeesResponseDTO();
		List<EmployeeEntity> employeeEntityList = empRepository.findAll();
		employeesResponse.setNoOfEmployees(employeeEntityList.size());
		List<EmployeeResponseDTO> employeesDTOList = employeeEntityList.stream().map(employeeMapper::entityToEmployeeDTO).toList();
		employeesResponse.setEmployeesList(employeesDTOList);

		return employeesResponse;
	}
	
	public EmployeeResponseDTO findEmployeeById(String email) throws Exception{
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		Optional<EmployeeEntity> employeeEntity = empRepository.findByEmail(email);
		if(employeeEntity.isPresent()) {
			responseDTO = employeeMapper.entityToEmployeeDTO(employeeEntity.get());
		}

		return responseDTO;
	}
	
	public EmployeeResponseDTO updateEmployee(CreateEmployeeRequestDTO employeeRequestDTO) throws Exception{

		EmployeeEntity existingEmployee = empRepository.findByEmail(employeeRequestDTO.getEmail()).orElseThrow(()
											-> new NullPointerException("Employee Not Found having email :" +
												employeeRequestDTO.getEmail()));

		existingEmployee = employeeMapper.dtoToEmployeeEntity(employeeRequestDTO);

		EmployeeEntity updatedEmployee = empRepository.save(existingEmployee);

        return employeeMapper.entityToEmployeeDTO(updatedEmployee);
	}
	
	public void deleteEmployee(String email) throws Exception{
		EmployeeEntity existingEmployee = empRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("Employee Not Found having email:" + email));

		empRepository.delete(existingEmployee);
	}
}
