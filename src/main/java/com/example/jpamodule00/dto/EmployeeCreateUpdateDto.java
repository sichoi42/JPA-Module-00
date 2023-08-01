package com.example.jpamodule00.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter @ToString
public class EmployeeCreateUpdateDto {

	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	private final Integer salary;
}
