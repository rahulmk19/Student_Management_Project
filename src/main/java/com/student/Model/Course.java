package com.student.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;

	@NotEmpty
	private String courseName;

	@NotEmpty
	private String description;

	@NotEmpty
	private String course_type;

	@NotNull
	private double duration;

	@NotEmpty
	private String topics;

	@ManyToMany(mappedBy = "courses")
	private Set<Student> students = new HashSet<>();
}
