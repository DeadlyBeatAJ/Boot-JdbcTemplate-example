package com.Deadlybeat.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.Deadlybeat.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee emp= new Employee();
		
		emp.setId(rs.getInt("ID"));
		emp.setName(rs.getString("NAME"));
		emp.setAge(rs.getInt("AGE"));
		emp.setSalary(rs.getInt("SALARY"));
		
		return emp;
	}

}
