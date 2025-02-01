package com.coderdot.apiservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderdot.dto.Student;

@Service
public class StudentApi {

	@Autowired
	private RestTemplate resttemplate;
	
	@Value("${student-crud-api}")
	private String baseurl;
	
	public List<Student> listStudent(){
		 String url = baseurl+"/getallStudent"; // Example API
	         List<Student> forObject = resttemplate.getForObject(url, ArrayList.class);
	         return forObject;
	}
	
	public String saveStudent(StudentRequest request) {
	    String url = baseurl + "/save"; 
	    ResponseEntity<String> response = resttemplate.postForEntity(url, request, String.class);
	    return response.getBody() != null ? response.getBody() : "Student saved successfully";
	}
	
	 public String updateStudent(StudentRequest request) {
	        String url = baseurl + "/update";

	        try {
	            ResponseEntity<String> response = resttemplate.exchange(
	                url, 
	                HttpMethod.PUT, 
	                new HttpEntity<>(request), 
	                String.class
	            );

	            return response.getBody() != null ? response.getBody() : "Student updated successfully";
	        } catch (RestClientException e) {
	            return "Error updating student: " + e.getMessage();
	        }
	    }
	 
	 public String deleteStudent(int id) {
		    String url = baseurl + "/delete/" + id; // Pass ID in the URL

		    try {
		        ResponseEntity<String> response = resttemplate.exchange(
		            url, 
		            HttpMethod.DELETE, 
		            null, // DELETE requests typically don't need a request body
		            String.class
		        );

		        return response.getBody() != null ? response.getBody() : "Student deleted successfully";
		    } catch (RestClientException e) {
		        return "Error deleting student: " + e.getMessage();
		    }
		}

}
