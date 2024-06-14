package com.ankit.controller;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.dto.ListEmployeesResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	

	@GetMapping("/employees")
	public ListEmployeesResponseDTO employeesList() {
		ListEmployeesResponseDTO listEmployeesResponseDTO = null;
		try {
			listEmployeesResponseDTO = empService.listAllEmployees();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			listEmployeesResponseDTO = new ListEmployeesResponseDTO();
		}
		return listEmployeesResponseDTO;
	}
	
	@PostMapping("/employee")
	public EmployeeResponseDTO createEmployeeHandler(@RequestBody CreateEmployeeRequestDTO employeeRequestDTO) {
		EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
		try {
			responseDTO = empService.createEmployee(employeeRequestDTO);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseDTO;
	}
	
	@PutMapping("/employee")
	public EmployeeResponseDTO updateEmployeeHandler(@RequestBody CreateEmployeeRequestDTO employeeRequestDTO) {
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
		try {
			employeeResponseDTO = empService.updateEmployee(employeeRequestDTO);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return employeeResponseDTO;
	}
	
	@GetMapping("/employee/{email}")
	public EmployeeResponseDTO retrieveEmployee(@PathVariable("email") String employeeEmail) {
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
		try {
			employeeResponseDTO = empService.findEmployeeById(employeeEmail);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return employeeResponseDTO;
	}
	
	@DeleteMapping("/employee/{email}")
	public void deleteEmployee(@PathVariable("email") String employeeEmail) {
		try {
			empService.deleteEmployee(employeeEmail);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
