package com.student.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;

	@Size(min = 3, max = 25, message = "Name should be greater then 3 and less than 25 character")
	private String adminName;

	@Email
	private String email;
	
	private String role;

//	@JsonProperty(access=Access.WRITE_ONLY)
	@Size(min = 8, message = "Password should be greater then or equal to 8 length")
	private String password;
}
