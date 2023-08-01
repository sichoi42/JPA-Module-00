package com.example.jpamodule00.project.service;

import com.example.jpamodule00.department.domain.Department;
import com.example.jpamodule00.dto.EmployeeDepartmentDto;
import com.example.jpamodule00.employee.domain.Employee;
import com.example.jpamodule00.employee.repository.EmployeeRepository;
import com.example.jpamodule00.project.repository.ProjectParticipationRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProjectService {

	private final ProjectParticipationRepository projectParticipationRepository;
	private final EmployeeRepository employeeRepository;

	public List<EmployeeDepartmentDto> getEmployeeDepartmentByProjectId(Long projectId) {
		log.info("Called getEmployeeDepartmentByProjectId, projectId = {}", projectId);
		List<Employee> employees = projectParticipationRepository.findEmployeeByProjectId(projectId);

		List<EmployeeDepartmentDto> employeeDepartmentDtos = new ArrayList<>();


		log.info("Result of getEmployeeDepartmentByProjectId, employees = {}", employees);
		for (Employee employee : employees) {
			Department department = employeeRepository.findDepartmentByEmployeeId(employee.getId());
			employeeDepartmentDtos.add(new EmployeeDepartmentDto(employee, department.getName()));
		}
		return employeeDepartmentDtos;
	}
}
