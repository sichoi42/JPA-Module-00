package com.example.jpamodule00.dto;

import com.example.jpamodule00.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter @ToString
public class EmployeeDepartmentDto {
	private Employee employee;
	private String departmentName;
}
