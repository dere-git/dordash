package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.dao.EmployeeEntity;
import com.dto.EmployeeDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeDao employeeDao;
	
	

	@Override
	public EmployeeDTO authenticate(String emailId, String password) {
		
		EmployeeEntity employeeEntity=employeeDao.authenticate(emailId, password);
		EmployeeDTO employeeDTO = new EmployeeDTO();//blank EmployeeDTO object
		BeanUtils.copyProperties(employeeEntity, employeeDTO);	
		
		return employeeDTO;
	}


	@Override
	public List<EmployeeDTO> giveAllEmp() {
		
		List<EmployeeEntity>  listOfEmpEntities=employeeDao.giveAllEmp();
		List<EmployeeDTO> dtoList=new ArrayList<>();
		
		for( EmployeeEntity  employeeEntity:listOfEmpEntities ){
			EmployeeDTO employeeDTO =new EmployeeDTO();
			BeanUtils.copyProperties(employeeEntity, employeeDTO);
			dtoList.add(employeeDTO);
			
			
		}
		
	
		return dtoList;
	}



	@Override
	public void deleteEmp(int employeeId) {
		
		employeeDao.deleteEmp(employeeId);
	}



	@Override
	public void saveEmp(EmployeeDTO empDTO) {
		EmployeeEntity employeeEntity=new EmployeeEntity();
		BeanUtils.copyProperties(empDTO, employeeEntity);
		employeeDao.saveEmp(employeeEntity);
		
	}


//edit implmentation 
	@Override
	public EmployeeDTO editEmp(int employeeId) {    // findById~editEmp

			EmployeeEntity employeeEntity=employeeDao.editEmp(employeeId);  //error
			EmployeeDTO employeeDTO = new EmployeeDTO();    //blank EmployeeDTO object
			BeanUtils.copyProperties(employeeEntity, employeeDTO);	
			
			return employeeDTO;
		}


	@Override
	public void updateditEmployee(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity=new EmployeeEntity();
		BeanUtils.copyProperties(employeeDTO, employeeEntity);
		employeeDao.updateEmployee(employeeEntity);
	}
	    
	    
	}


