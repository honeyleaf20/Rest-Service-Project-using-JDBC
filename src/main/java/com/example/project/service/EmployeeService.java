package com.example.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.employeeDAO.EmployeeDAO;
import com.example.project.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDAO employeeDAO;
	public  Employee getEmployee(int id) throws SQLException {
		return employeeDAO.getEmployee(id);
	}
	

	public  List<Employee> getAllEmployee() throws SQLException {
		return employeeDAO.getAllEmployee();
		
	}


	public  Employee addEmployee(Employee newemp) throws SQLException {
		
		return employeeDAO.addEmployee(newemp);
	}

}
