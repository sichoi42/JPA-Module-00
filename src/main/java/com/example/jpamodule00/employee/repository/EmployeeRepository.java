package com.example.jpamodule00.employee.repository;

import com.example.jpamodule00.department.domain.Department;
import com.example.jpamodule00.employee.domain.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select d from Department d join fetch d.employees e where e.id = :employeeId")
	Department findDepartmentByEmployeeId(Long employeeId);
}
