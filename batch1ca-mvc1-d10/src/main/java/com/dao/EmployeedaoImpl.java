package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeedaoImpl implements EmployeeDao{

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public EmployeeEntity authenticate(String emailId, String password) {
		EmployeeEntity employeeEntity=null;
		
		String sql="select * from employee where emailId =? and password=?";
		Object[] data={emailId,password};
		try{
		employeeEntity=jdbcTemplate.queryForObject(sql, data, new BeanPropertyRowMapper<>(EmployeeEntity.class));
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		return employeeEntity;
	}

	@Override
	public List<EmployeeEntity> giveAllEmp() {
		 String sql="select * from employee ";
		 List<EmployeeEntity> e=(List)jdbcTemplate.query(sql, new BeanPropertyRowMapper(EmployeeEntity.class));
		return e;
	}

	@Override
	public void deleteEmp(int employeeId) {
		
		jdbcTemplate.update("delete from employee where employeeId= ?",employeeId);	
		
		
		
	}

	@Override
	public void saveEmp(EmployeeEntity employeeEntity) {
	
		String sql="insert into employee(employeeId,employeeName,salary,emailId,password)values(?,?,?,?,?)";
	     Object[] data={employeeEntity.getEmployeeId(),employeeEntity.getEmployeeName(),employeeEntity.getSalary(),employeeEntity.getEmailId(),employeeEntity.getPassword()};
	     jdbcTemplate.update(sql,data);
		
			
	}

	
	//edit imlementation
	@Override
	public EmployeeEntity editEmp(int employeeId) {

		EmployeeEntity employeeEntity=null;
		
		String sql="update  employee set employeeName =?,salary=?,emailId=?,password=? where employeeId =? ";
		Object[] data={ employeeId};
		//Object[] data={ employeeId};
		try{
		employeeEntity=jdbcTemplate.queryForObject(sql, data, new BeanPropertyRowMapper<>(EmployeeEntity.class));
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
				
		return employeeEntity;
	}

	@Override
	public void updateEmployee(EmployeeEntity employeeEntity) {
		String sql = "update employee set employeeName=?,salary=?,emailId=?,password=? where employeeId=?";
		Object[] data= {employeeEntity.getEmployeeName() ,employeeEntity.getSalary(),employeeEntity.getEmailId(),employeeEntity.getPassword(),employeeEntity.getEmployeeId()};
		jdbcTemplate.update(sql,data);

	}
	


}
