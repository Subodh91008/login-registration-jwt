package com.coderdot.controllers;

import com.coderdot.apiservice.StudentApi;
import com.coderdot.apiservice.StudentRequest;
import com.coderdot.dto.HelloResponse;
import com.coderdot.dto.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent(@RequestBody StudentRequest request){
    	studentApi.saveStudent(request);
    	return ResponseEntity.ok("Student recorrd saved succesfully");
    }
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentRequest request){
    	studentApi.updateStudent(request);
    	return ResponseEntity.ok("Student record updated succesfully");
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
    	studentApi.deleteStudent(id);
    	return ResponseEntity.ok("Student record Deleted Succesfully");
    }
    
}
