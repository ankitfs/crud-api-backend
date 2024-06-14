package com.ankit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListEmployeesResponseDTO {

    private int noOfEmployees;
    private List<EmployeeResponseDTO> employeesList;

}
