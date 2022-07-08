package com.example.project.controller;

import java.sql.SQLException;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.model.Employee;
import com.example.project.service.EmployeeService;

	@RestController
	public class Controller {
		@Autowired
		EmployeeService employeeService;
		
	@GetMapping(path="/getAllEmployee")
	public List<Employee> getAllEmployee() throws SQLException{
		return  employeeService.getAllEmployee();
	
	}
		
	 @GetMapping(path="/getDetails/{id}")
	 public ResponseEntity<Employee> getDetails(@PathVariable Integer id) throws Exception, SQLException {
		 try {
	  Employee emp= employeeService.getEmployee(id);
	  return ResponseEntity.ok(emp);
	}
		 catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	 
	 @PostMapping("/addEmployee")
	 public Employee addEmployee(@RequestBody Employee newemp) throws SQLException {
		 
		  Employee emp=employeeService.addEmployee(newemp);
		  return emp;
	
	 }
	}


