package com.example.jpamodule00.employee.controller;

import com.example.jpamodule00.dto.EmployeeCreateUpdateDto;
import com.example.jpamodule00.employee.domain.Employee;
import com.example.jpamodule00.employee.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/employees")
@Log4j2
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping("/{departmentId}")
	public void createEmployee(
			@RequestBody EmployeeCreateUpdateDto employeeCreateUpdateDto,
			@PathVariable("departmentId") Long departmentId,
			HttpServletResponse response
	) {
		log.info("Called createEmployee employeeCreateUpdateDto = {}, departmentId = {}", employeeCreateUpdateDto, departmentId);
		try {
			employeeService.createEmployee(employeeCreateUpdateDto, departmentId);
		} catch (HttpClientErrorException e) {
			response.setStatus(e.getStatusCode().value());
		}
		catch (Exception e) {
			log.error("Error occurred while creating employee", e);
		}
	}

	@GetMapping("/{employeeId}")
	public Employee getEmployeesByDepartmentId(
			@PathVariable("employeeId") Long employeeId,
			HttpServletResponse response
	) {
		log.info("Called getEmployeesByDepartmentId, employeeId = {}", employeeId);
		try {
			return employeeService.getEmployeesByDepartmentId(employeeId);
		} catch (HttpClientErrorException e) {
			response.setStatus(e.getStatusCode().value());
		}
		catch (Exception e) {
			log.error("Error occurred while getting employees", e);
		}
		return null;
	}

	@PatchMapping("/{employeeId}")
	public void updateEmployee(
			@PathVariable("employeeId") Long employeeId,
			@RequestBody EmployeeCreateUpdateDto employeeCreateUpdateDto,
			HttpServletResponse response
	) {
		log.info("Called updateEmployee, employeeId = {}, employeeCreateUpdateDto = {}", employeeId, employeeCreateUpdateDto);
		try {
			employeeService.updateEmployee(employeeCreateUpdateDto, employeeId);
		} catch (HttpClientErrorException e) {
			response.setStatus(e.getStatusCode().value());
		}
		catch (Exception e) {
			log.error("Error occurred while updating employee", e);
		}
	}

	@DeleteMapping("/{employeeId}")
	public void deleteEmployee(
			@PathVariable("employeeId") Long employeeId,
			HttpServletResponse response
	) {
		log.info("Called deleteEmployee, employeeId = {}", employeeId);
		try {
			employeeService.deleteEmployee(employeeId);
		} catch (HttpClientErrorException e) {
			response.setStatus(e.getStatusCode().value());
		}
		catch (Exception e) {
			log.error("Error occurred while deleting employee", e);
		}
	}
}
