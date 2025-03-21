package com.coderdot.apiservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

	private String name;
	private String rollnumber;
	private String subject;
	private String address;
	private String phone;
	private String email;
}
