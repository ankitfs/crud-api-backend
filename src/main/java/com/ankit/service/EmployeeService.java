package com.ankit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.entity.Employee;
import com.ankit.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	public Employee createEmployee(Employee employee) {
				
		Employee createdEmployee = empRepository.save(employee);
		
		return createdEmployee;
	}
	
	public List<Employee> listAllEmployees() {
		return empRepository.findAll();
	}
	
	public Optional<Employee> findEmployeeById(int id) {
		return empRepository.findById(id);
	}
	
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> existingEmployee = findEmployeeById(employee.getId());
		Employee updatedEmployee = null;
		if(existingEmployee.isPresent()) {
			updatedEmployee = existingEmployee.get();
			updatedEmployee.setName(employee.getName());
			updatedEmployee.setEmail(employee.getEmail());
			
			updatedEmployee = empRepository.save(updatedEmployee);
		}
		return updatedEmployee;
		//Integer updatedEmployeeStatus = empRepository.updateEmployee(employee.getName(), employee.getEmail(), empId);
		//return updatedEmployeeStatus;
	}
	
	public void deleteEmployee(int id) {
		empRepository.deleteById(id);
	}
}
