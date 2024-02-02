package com.student.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

	@NotEmpty
	private String courseName;

	@NotEmpty
	private String description;

	@NotEmpty
	private String courseType;

	@NotNull
	private double duration;

	@NotEmpty
	private String topics;
}
