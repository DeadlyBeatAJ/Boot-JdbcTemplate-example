package com.Deadlybeat.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Deadlybeat.mapper.EmployeeRowMapper;
import com.Deadlybeat.model.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate template;
	
	//add new Employee
	@Transactional
	public Employee createEmployee(Employee emp)
	{
		String sql="INSERT INTO EMPLOYEE"+"(NAME,AGE,SALARY) VALUES (?,?,?)";
		
		KeyHolder holder= new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
			{
				PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, emp.getName());
				ps.setInt(2, emp.getAge());
				ps.setInt(3, emp.getSalary());
				return ps;
			}
		},holder);
		
		int generatedEmpID=holder.getKey().intValue();
		emp.setId(generatedEmpID);
		return emp;		
	}
	
	//get all Employee
	public List<Employee> getAllEmployees()
	{
		String sql="SELECT * FROM EMPLOYEE";
		return template.query(sql, new EmployeeRowMapper());
	}
	
	//get Employee based on the ID
	@Transactional(readOnly = true)
	public Employee getEmployeeByID(int id)
	{
		String sql= "SELECT * FROM EMPLOYEE WHERE ID=?";
		Employee emp= template.queryForObject(sql, new Object[] {id},  new EmployeeRowMapper());
		return emp;
	}
	
	//update an employee
	public void updateEmployee(int id, Employee emp)
	{
		String sql="UPDATE EMPLOYEE SET NAME=?, AGE=?, SALARY=? WHERE ID=?";
		template.update(sql, emp.getName(), emp.getAge(), emp.getSalary(), id);
	}
	
	//delete an employee
	public void deleteEmployee(int id)
	{
		String sql="DELETE FROM EMPLOYEE WHERE ID=?";
		template.update(sql, id); 
	}
	
	//check if employee is present or not based on count
	public boolean isEmployeeExist(int id)
	{
		String sql = "SELECT COUNT(*) FROM EMPLOYEE where id=?";

		int count = template.queryForObject(sql, new Object[] { id }, Integer.class);
		if (count >= 1)
		{
			return true;
		}
		return false;
	}
}
