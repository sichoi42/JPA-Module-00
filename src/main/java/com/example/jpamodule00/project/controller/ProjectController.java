package com.example.jpamodule00.project.controller;

import com.example.jpamodule00.dto.EmployeeDepartmentDto;
import com.example.jpamodule00.project.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/projects")
@Log4j2
public class ProjectController {

	private final ProjectService projectService;

	/**
	 * Project가 {projectId}인 Project에 속한 Employees의 정보와 그 employee가 속한 Department의 정보를 조회
	 *
	 * @param projectId 조회할 Project의 id
	 * @return {@link EmployeeDepartmentDto}
	 */
	@GetMapping("/{projectId}/employees/department")
	public List<EmployeeDepartmentDto> getEmployeeDepartmentByDepartmentId(
			@PathVariable("projectId") Long projectId
	) {
		log.info("Called getEmployeeDepartmentByDepartmentId, projectId = {}", projectId);
		return projectService.getEmployeeDepartmentByProjectId(projectId);
	}
}
