package com.coderdot.controllers;

import com.coderdot.apiservice.StudentApi;
import com.coderdot.dto.HelloResponse;
import com.coderdot.dto.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
	@Autowired
	private StudentApi studentApi;

    @GetMapping("/hello")
    public HelloResponse hello(){
        return new HelloResponse("Hello from Authorized API request.");
    }
    @GetMapping("/allstudent")
  public ResponseEntity<List<Student>> getallstudent(){
	  List<Student> listStudent = studentApi.listStudent();
	  return ResponseEntity.ok(listStudent);
  }
}
