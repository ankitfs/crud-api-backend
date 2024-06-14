package com.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ankit.entity.EmployeeEntity;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

    Optional<EmployeeEntity> findByEmail(String email) throws Exception;

    Long deleteByEmail(String email) throws Exception;

}
