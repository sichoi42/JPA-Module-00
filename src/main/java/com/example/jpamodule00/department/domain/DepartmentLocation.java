package com.example.jpamodule00.department.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "DEPARTMENT_LOCATION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString @Log4j2 @EqualsAndHashCode
public class DepartmentLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_LOCATION_ID")
	private Long id;

	@Column(name = "NAME", length = 32, nullable = false)
	private String name;

	@OneToMany(mappedBy = "departmentLocation",
			targetEntity = Department.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Department> departments;

	protected DepartmentLocation(String name) {
		this.name = name;
	}

	public static DepartmentLocation of(String name) {
		DepartmentLocation departmentLocation = new DepartmentLocation(name);
		if (!departmentLocation.isValid()) {
			throw new IllegalArgumentException("invalid argument");
		}
		return departmentLocation;
	}

	private boolean isValid() {
		if (this.name == null || this.name.isEmpty()) {
			log.error("name is null or empty");
			return false;
		}
		return true;
	}
}
