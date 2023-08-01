package com.example.jpamodule00.department.domain;

import com.example.jpamodule00.employee.domain.Employee;
import com.example.jpamodule00.project.domain.Project;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Entity
@Table(name = "DEPARTMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Log4j2 @EqualsAndHashCode
@ToString(exclude = {"employees", "projects", "departmentLocation"})
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ID")
	private Long id;

	@Column(name = "NAME", length = 32, nullable = false)
	private String name;

	@Column(name = "CODE", length = 32, nullable = false)
	private Integer code;

	@Column(name = "DESCRIPTION", length = 128, nullable = false)
	private String description;

	@JoinColumn(name = "DEPARTMENT_LOCATION_ID")
	@ManyToOne(targetEntity = DepartmentLocation.class)
	private DepartmentLocation departmentLocation;

	@OneToMany(mappedBy = "workingDepartment",
			targetEntity = Employee.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Employee> employees;

	@OneToMany(mappedBy = "department",
			targetEntity = Project.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Project> projects;

	protected Department(String name, Integer code, String description) {
		this.name = name;
		this.code = code;
		this.description = description;
	}

	public static Department of(String name, Integer code, String description) {
		Department department = new Department(name, code, description);
		if (!department.isValid()) {
			throw new IllegalArgumentException("invalid argument");
		}
		return department;
	}

	private boolean isValid() {
		if (this.name == null || this.name.isEmpty()) {
			log.error("name is null or empty");
			return false;
		}
		if (this.code == null || this.code == 0) {
			log.error("code is null or empty");
			return false;
		}
		if (this.description == null || this.description.isEmpty()) {
			log.error("description is null or empty");
			return false;
		}
		return true;
	}
}
