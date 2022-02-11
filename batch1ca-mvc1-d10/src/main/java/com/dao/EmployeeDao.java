package com.dao;

import java.util.List;

public interface EmployeeDao {
	
	EmployeeEntity authenticate(String emailId,String password);

	List<EmployeeEntity> giveAllEmp();

	void deleteEmp(int employeeId);

	void saveEmp(EmployeeEntity employeeEntity);
	EmployeeEntity editEmp(int employeeId);
//
	void updateEmployee(EmployeeEntity employeeEntity);
	

}
