package com.example.jpamodule00.project.domain;

import com.example.jpamodule00.department.domain.Department;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "PROJECT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @ToString @Log4j2 @EqualsAndHashCode
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private Long id;

	@Column(name = "NAME", length = 32, nullable = false)
	private String name;

	@Column(name = "CODE", length = 32, nullable = false)
	private Integer code;

	@JoinColumn(name = "DEPARTMENT_ID")
	@ManyToOne(targetEntity = Department.class)
	private Department department;

	@OneToMany(mappedBy = "project")
	private Set<ProjectParticipation> projectParticipations;
}
