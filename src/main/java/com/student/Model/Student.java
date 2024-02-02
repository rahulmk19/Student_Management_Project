package com.student.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long student_id;

	@Size(min = 3, max = 25, message = "Student name must be between {min} and {max} characters")
	private String studentName;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "Date of birth cannot be null")
	private LocalDate dateOfBirth;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotEmpty(message = "Unique student code cannot be empty")
	@Column(unique = true)
	private String studentCode;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Pattern(regexp = "\\d{10}", message = "Mobile Number must be consist of 10 digits")
	private String mobileNumber;

	@NotEmpty(message = "Email should not be Empty!!")
	@Email(message = "Email should be formatted!!")
	private String email;

//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 8, message = "Password should be greater then or equal to 8 length")
	private String password;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Set<Address> address = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> courses = new HashSet<>();

}
