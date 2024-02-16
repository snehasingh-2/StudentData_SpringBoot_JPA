package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	StudentRepository sr;
	
	@GetMapping("/listallStudent")
	public List<Student> viewStudent()
	{     
	  	 return sr.findAll();
		
	}
	
	@GetMapping("/searchStudent/{id}")
	public Optional<Student> searchStudent(@PathVariable int id)
	{     
	  	 return sr.findById(id);
		
	}
	
    @PostMapping("/addStudent")
	public String addStudent(@RequestBody Student s)
	{
	     sr.save(s);
	  	 return " data added";	
	}
    
    @PutMapping("/updateStudent/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student s)
	{
    	Optional<Student> k= sr.findById(id);// object
		 if(k.isPresent())
		 {
			 Student n1= k.get();
		     n1.setFirstName(s.getFirstName());
		     n1.setLastName(s.getLastName());
		     n1.setAge(s.getAge());
		     sr.save(n1);
		 
		 return  new ResponseEntity<>("student "+id+"  found", HttpStatus.OK);	 
		 }
		 else
				return new ResponseEntity<>("student "+id+"not found", HttpStatus.NOT_FOUND);
	}
   
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id)
	{
		
		if(sr.existsById(id))
		{
		 sr.deleteById(id);
	     return new ResponseEntity<>("student "+id+" deleted", HttpStatus.OK);
	  }
		
		return new ResponseEntity<>("student "+id+" not found", HttpStatus.NOT_FOUND);
	}
	
}
