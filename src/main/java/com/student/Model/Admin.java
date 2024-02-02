package com.student.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

	@Size(min = 3, max = 25, message = "Admin name must be between {min} and {max} characters")
	private String adminName;

	@NotEmpty(message = "Email should not be Empty!!")
	@Email(message = "Email should be formatted!!")
	private String email;

//	@JsonProperty(access=Access.WRITE_ONLY)
	@Size(min = 8, message = "Password should be greater then or equal to 8 length")
	private String password;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private Role role;
}
