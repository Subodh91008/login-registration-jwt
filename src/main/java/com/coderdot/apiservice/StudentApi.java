package com.coderdot.apiservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderdot.dto.Student;

@Service
public class StudentApi {

	@Autowired
	private RestTemplate resttemplate;
	
	@Value("${student-crud-api}")
	private String baseurl;
	
	public List<Student> listStudent(){
		 String url = baseurl+"/student/getallStudent"; // Example API
	         List<Student> forObject = resttemplate.getForObject(url, ArrayList.class);
	         return forObject;
	}
}
