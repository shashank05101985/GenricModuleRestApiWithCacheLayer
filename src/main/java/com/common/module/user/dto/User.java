package com.common.module.user.dto;

import com.common.base.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends BaseDTO {

	private String email;

	@JsonIgnore
	private String password;

	private String firstName;
	
	private String lastName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
