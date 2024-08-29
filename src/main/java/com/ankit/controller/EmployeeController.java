package com.ankit.controller;

import com.ankit.dto.CreateEmployeeRequestDTO;
import com.ankit.dto.EmployeeResponseDTO;
import com.ankit.dto.ListEmployeesResponseDTO;
import com.ankit.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	

	@GetMapping("/employees")
	public ListEmployeesResponseDTO employeesList() {
		return empService.listAllEmployees();
	}
	
	@PostMapping("/employee")
	public EmployeeResponseDTO createEmployeeHandler
			(@RequestBody CreateEmployeeRequestDTO employeeRequestDTO) {
		return empService.createEmployee(employeeRequestDTO);
	}
	
	@PutMapping("/employee")
	public EmployeeResponseDTO updateEmployeeHandler(@RequestBody CreateEmployeeRequestDTO employeeRequestDTO) {
		return empService.updateEmployee(employeeRequestDTO);
	}
	
	@GetMapping("/employee/{email}")
	public EmployeeResponseDTO retrieveEmployee(@PathVariable("email") String employeeEmail) {
		return empService.findEmployeeById(employeeEmail);
	}
	
	@DeleteMapping("/employee/{email}")
	public void deleteEmployee(@PathVariable("email") String employeeEmail) {
		empService.deleteEmployee(employeeEmail);
	}
}
