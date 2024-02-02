package com.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private String area;
	private String state;
	private String district;
	private String pincode;
	private String addressType;

}
