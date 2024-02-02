package com.student.DTO;

import java.time.LocalDate;

import com.student.Model.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOUpdate {

	private String studentName;

	private LocalDate dateOfBirth;

	private Gender gender;

	private String studentCode;

	private String email;

	private String mobileNo;

	private String parentName;
}
