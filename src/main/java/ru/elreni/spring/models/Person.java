package ru.elreni.spring.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
	
	int personId;
	
	@NotEmpty(message = "Full name should be not empty")
	@Size(min = 1, max = 100, message = "Full name length should be in range 1-100 symbols")
	String fullName;
	
	@Min(value = 1900, message = "Year of birth should be greater or equal 1900")
	int yearOfBirth;
	
	@NotEmpty(message = "Email should be not empty")
	@Email(message = "Email should be valid")
	String email;
	
	public Person(int id, String fullName, int yearOfBirth, String email) {
		super();
		this.personId = id;
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
		this.email = email;
	}

	public Person() {
		
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int id) {
		this.personId = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
