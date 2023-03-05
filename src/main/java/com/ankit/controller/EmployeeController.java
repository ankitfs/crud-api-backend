package com.ankit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.entity.Employee;
import com.ankit.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	

	@GetMapping("/employees")
	public List<Employee> employeesList() {		
		return empService.listAllEmployees();
	}
	
	@PostMapping("/employee")
	public Employee createEmployeeHandler(@RequestBody Employee employeeBody) {
		return empService.createEmployee(employeeBody);
	}
	
	@PutMapping("/employee")
	public Employee updateEmployeeHandler(@RequestBody Employee employeeBody) {
		return empService.updateEmployee(employeeBody);
	}
	
	@GetMapping("/employee/{id}")
	public Employee retrieveEmployee(@PathVariable("id") Integer employeeid) {
		return empService.findEmployeeById(employeeid).get();
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") Integer employeeid) {
		empService.deleteEmployee(employeeid);
	}
}
