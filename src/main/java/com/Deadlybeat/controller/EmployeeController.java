package com.Deadlybeat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Deadlybeat.exception.EmployeeNotFoundException;
import com.Deadlybeat.model.Employee;
import com.Deadlybeat.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	//add new Employee record
	@PostMapping("/AddEmployee")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp)
	{	
		emp=service.createEmployee(emp);
		return new ResponseEntity<>("New Employee is added with ID:- "+emp.getId(),HttpStatus.CREATED);
	}
	
	
	//get all the Employee records
	@GetMapping("/getAllEmployees")
	public ResponseEntity<Object> getAllEmployees()
	{	
		List<Employee> employeeList= service.getAllEmployees();
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	
	//get an employee record based on ID
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Object> getEmployeeByID(@PathVariable("id") int id) {
		
		//check if record is present for this id 
		boolean isEmployeeExist=service.isEmployeeExist(id);
		
		if(isEmployeeExist)
		 {
			Employee emp=service.getEmployeeByID(id);
			return new ResponseEntity<>(emp,HttpStatus.OK);
		 }
		else {
			throw new EmployeeNotFoundException();
		}
	}
	
	//update employee based on ID 
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateEmplouyee(@PathVariable("id") int id, @RequestBody Employee emp)
	{
		//check if record is present for this id 
		boolean isEmployeeExist=service.isEmployeeExist(id);
		
		if(isEmployeeExist)
		{
			service.updateEmployee(id, emp);
			return new ResponseEntity<Object>("Employee record updated Successfully!!", HttpStatus.OK);
		}
		else {
			throw new EmployeeNotFoundException();
		}
		
	}
	
	//delete employee and return string
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id) 
	{
		//check if record is present for this id 
		boolean isEmployeeExist=service.isEmployeeExist(id);
		
		if(isEmployeeExist)
		{
			service.deleteEmployee(id);
			return new ResponseEntity<Object>("Employee record deleted Successfully!!", HttpStatus.OK);
		}
		else {
			throw new EmployeeNotFoundException();
		}
	}
	
	//delete Employee and return remaining employees data
	@DeleteMapping("/deleteEmployeebyID/{id}")
	public ResponseEntity<Object> deleteEmployeeByID(@PathVariable("id") int id) 
	{
		//check if record is present for this id 
		boolean isEmployeeExist=service.isEmployeeExist(id);
		
		if(isEmployeeExist)
		{
			List<Employee> employeeList=service.deleteEmployeebyID(id);
			return new ResponseEntity<Object>(employeeList, HttpStatus.OK);
		}
		else {
			throw new EmployeeNotFoundException();
		}
	}
}

