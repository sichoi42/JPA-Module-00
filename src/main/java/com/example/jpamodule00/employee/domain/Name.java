package com.example.jpamodule00.employee.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

/**
 * Value Object
 */
@Getter @ToString @Log4j2 @EqualsAndHashCode
public class Name {

	private final String firstName;
	private final String lastName;

	protected Name() {
		this.firstName = "";
		this.lastName = "";
	}

	public Name(String firstName, String lastName) {
		if (firstName == null || lastName == null) {
			throw new IllegalArgumentException("Names cannot be null");
		}
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean isValid() {
		return !this.firstName.isEmpty() && !this.lastName.isEmpty();
	}
}
