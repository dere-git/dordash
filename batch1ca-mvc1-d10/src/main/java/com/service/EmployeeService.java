package com.service;

import java.util.List;

import com.dto.EmployeeDTO;

public interface EmployeeService {
	
	EmployeeDTO authenticate(String emailId,String password);

	List<EmployeeDTO> giveAllEmp();

	void deleteEmp(int employeeId);

	void saveEmp(EmployeeDTO empDTO);

	EmployeeDTO editEmp(int employeeId);

	void updateditEmployee(EmployeeDTO employeeDTO);

}
