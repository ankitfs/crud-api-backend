package com.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ankit.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("update Employee e set e.name = :employeeName, e.email = :employeeEmail where e.id = :employeeid "
	 * ) int updateEmployee(@Param("employeeName") String
	 * eName, @Param("employeeEmail") String email, @Param("employeeid") Integer
	 * eId);
	 */
	
}
