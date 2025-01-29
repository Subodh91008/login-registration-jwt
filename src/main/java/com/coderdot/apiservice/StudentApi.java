package com.coderdot.apiservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderdot.dto.Student;

@Service
public class StudentApi {

	@Autowired
	private RestTemplate resttemplate;
	
	public List<Student> listStudent(){
		 String url = "http://localhost:8181/student/getallStudent"; // Example API
	         List<Student> forObject = resttemplate.getForObject(url, ArrayList.class);
	         return forObject;
	}
}
