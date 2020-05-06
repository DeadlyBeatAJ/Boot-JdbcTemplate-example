package com.Deadlybeat.service;

import java.util.List;

import com.Deadlybeat.model.Employee;

public interface EmployeeService {

	public abstract Employee createEmployee(Employee emp);
	
	public abstract List<Employee> getAllEmployees(); 
	
	public abstract Employee getEmployeeByID(int id); 
	
	public abstract void updateEmployee(int id, Employee emp);
	
	public abstract void deleteEmployee(int id); 
	
	public abstract List<Employee> deleteEmployeebyID(int id);

	public abstract boolean isEmployeeExist(int id);
}
