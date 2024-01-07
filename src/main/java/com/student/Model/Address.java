package com.student.Model;

import java.util.Objects;

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
	private Long address_id;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressType, other.addressType) && Objects.equals(address_id, other.address_id)
				&& Objects.equals(area, other.area) && Objects.equals(district, other.district)
				&& Objects.equals(pincode, other.pincode) && Objects.equals(state, other.state)
				&& Objects.equals(student, other.student);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressType, address_id, area, district, pincode, state, student);
	}

}
