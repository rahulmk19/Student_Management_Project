package com.student.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@NotEmpty
	private String area;

	@NotEmpty
	private String state;

	@NotEmpty
	private String district;

	@NotEmpty
	@Size(min = 6, max = 6, message = "Pincode Must be exactly 6 digits")
	private String pincode;

	@NotEmpty
	private String addressType;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

}
