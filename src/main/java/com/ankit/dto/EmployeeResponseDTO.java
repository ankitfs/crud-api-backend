package com.ankit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO extends CreateEmployeeRequestDTO{

    Timestamp createdDate;

}
