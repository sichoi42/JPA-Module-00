package com.example.jpamodule00.project.repository;

import com.example.jpamodule00.employee.domain.Employee;
import com.example.jpamodule00.project.domain.Project;
import com.example.jpamodule00.project.domain.ProjectParticipation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectParticipationRepository extends JpaRepository<ProjectParticipation, Long> {

	@Query("select p.employee from ProjectParticipation p where p.project.id = :projectId")
	List<Employee> findEmployeeByProjectId(Long projectId);
}