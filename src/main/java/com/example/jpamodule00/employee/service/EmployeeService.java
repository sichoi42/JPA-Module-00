package com.example.jpamodule00.employee.service;

import com.example.jpamodule00.department.domain.Department;
import com.example.jpamodule00.department.repository.DepartmentRepository;
import com.example.jpamodule00.dto.EmployeeCreateUpdateDto;
import com.example.jpamodule00.employee.domain.Employee;
import com.example.jpamodule00.employee.domain.Name;
import com.example.jpamodule00.employee.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;

	public void createEmployee(EmployeeCreateUpdateDto employeeCreateUpdateDto, Long departmentId) {
		log.info("Called createEmployee employeeCreateUpdateDto = {}, departmentId = {}", employeeCreateUpdateDto, departmentId);
		Name name = new Name(employeeCreateUpdateDto.getFirstName(), employeeCreateUpdateDto.getLastName());
		Optional<Department> department = departmentRepository.findById(departmentId);
		if (department.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Department not found");
		}
		Employee employee = Employee.of(
				name,
				employeeCreateUpdateDto.getPhoneNumber(),
				employeeCreateUpdateDto.getSalary(),
				department.get()
		);
		employeeRepository.save(employee);
	}

	public Employee getEmployeesByDepartmentId(Long employeeId) {
		log.info("Called getEmployeesByDepartmentId, employeeId = {}", employeeId);
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		return employee.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee not found"));
	}

	public void updateEmployee(EmployeeCreateUpdateDto employeeCreateUpdateDto, Long employeeId) {
		log.info("Called updateEmployee employeeCreateUpdateDto = {}, employeeId = {}", employeeCreateUpdateDto, employeeId);
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (employee.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee not found");
		}
		Name name = new Name(employeeCreateUpdateDto.getFirstName(), employeeCreateUpdateDto.getLastName());
		employee.get().changeName(name);
		employee.get().changePhoneNumber(employeeCreateUpdateDto.getPhoneNumber());
		employee.get().changeSalary(employeeCreateUpdateDto.getSalary());
		employeeRepository.save(employee.get());
	}

	public void deleteEmployee(Long employeeId) {
		log.info("Called deleteEmployee, employeeId = {}", employeeId);
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (employee.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee not found");
		}
		employeeRepository.delete(employee.get());
	}
}
