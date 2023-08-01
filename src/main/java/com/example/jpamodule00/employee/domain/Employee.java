package com.example.jpamodule00.employee.domain;

import com.example.jpamodule00.department.domain.Department;
import com.example.jpamodule00.project.domain.ProjectParticipation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Log4j2 @EqualsAndHashCode
@ToString(exclude = {"workingDepartment", "projectParticipations"})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Long id;

	@Embedded
	@Column(name = "NAME", length = 32, nullable = false)
	private Name name;


	@Column(name = "PHONE_NUMBER", length = 32, nullable = false)
	private String phoneNumber;

	@Column(name = "SALARY", length = 32, nullable = false)
	private Integer salary;

	@JsonIgnore
	@JoinColumn(name = "DEPARTMENT_ID")
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Department.class)
	private Department workingDepartment;

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private Set<ProjectParticipation> projectParticipations;

	protected Employee(Name name, String phoneNumber, Integer salary, Department workingDepartment) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		this.workingDepartment = workingDepartment;
	}

	public static Employee of(Name name, String phoneNumber, Integer salary, Department workingDepartment) {
		Employee employee = new Employee(name, phoneNumber, salary, workingDepartment);
		if (!employee.isValid()) {
			throw new IllegalArgumentException("invalid argument");
		}
		return employee;
	}

	private boolean isValid() {
		if (this.name == null || !this.name.isValid()) {
			log.error("name is null or empty");
			return false;
		}
		return true;
	}

	public void changeName(Name name) {
		if (name == null || !name.isValid()) {
			log.error("name is null or empty");
			throw new IllegalArgumentException("invalid argument");
		}
		this.name = name;
	}

	public void changePhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			log.error("phoneNumber is null or empty");
			throw new IllegalArgumentException("invalid argument");
		}
		this.phoneNumber = phoneNumber;
	}

	public void changeSalary(Integer salary) {
		if (salary == null || salary < 0) {
			log.error("salary is null or negative");
			throw new IllegalArgumentException("invalid argument");
		}
		this.salary = salary;
	}
}
