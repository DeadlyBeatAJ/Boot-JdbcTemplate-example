package com.Deadlybeat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Deadlybeat.model.Employee;
import com.Deadlybeat.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public Employee createEmployee(Employee emp) {
		
		return repository.createEmployee(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return repository.getAllEmployees();
	}

	@Override
	public Employee getEmployeeByID(int id) {
		
		return repository.getEmployeeByID(id);
	}

	@Override
	public void updateEmployee(int id, Employee emp) {
		
		repository.updateEmployee(id, emp);
		
	}

	@Override
	public void deleteEmployee(int id) {
		
		repository.deleteEmployee(id);
		
	}

	@Override
	public List<Employee> deleteEmployeebyID(int id) {
		repository.deleteEmployee(id);
		return repository.getAllEmployees();
	}

	@Override
	public boolean isEmployeeExist(int id) {
		
		return repository.isEmployeeExist(id);

	}
}
