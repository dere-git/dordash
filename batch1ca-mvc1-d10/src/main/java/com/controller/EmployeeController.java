package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.EmployeeDTO;
import com.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	//EmployeeDao employeeDao;
	
	EmployeeService employeeService;
	
	
	@GetMapping("/")
	String displayLoginPage(){
		
		return "login";//login.jsp
		
	}
	
	
	@PostMapping("login")
	String authenticate(@RequestParam String email ,@RequestParam String pswd ,Model model){  //HttpServletRequest request
		
		System.out.println(email +"   "+pswd);
		
		EmployeeDTO employeeDTO=employeeService.authenticate(email, pswd);
		
		if (employeeDTO!=null){
			model.addAttribute("employeeDTO", employeeDTO);
			
			return "congrates";
		}
		
		
		
		return "login";//
		
	}
	
	@GetMapping("showAllEmployee")
	String showAll(Model model){
		
		List<EmployeeDTO> listOfEmp=employeeService.giveAllEmp();
		
		model.addAttribute("listOfEmp", listOfEmp);
		
		return "showAll";//showAll.jsp
		
	}
	
	
	@GetMapping("deleteEmployee")
	String delEmployee(@RequestParam int employeeId,Model model){
		
		employeeService.deleteEmp(employeeId);

		/*List<EmployeeDTO> listOfEmp=employeeService.giveAllEmp();
		model.addAttribute("listOfEmp", listOfEmp);		
		return "showAll";//showAll.jsp
*/	
		return "redirect:showAllEmployee";
		
	}

	
	//editemployee record 
	@GetMapping("showeditEmployee")
	 String showeditEmployee(@RequestParam ("employeeId") int employeeId,Model model){//editProfileAction
		System.out.println(employeeId+"MMMMMMMMMMMMMMMMMM");
		
		EmployeeDTO employeeDTO=employeeService.editEmp(employeeId);//findById~ editEmp
		model.addAttribute("EmployeeDTO", employeeDTO);

		return "editpage";
		
		//String showUpdateForm(@RequestParam int employeeId,Model model){
			//EmployeeDTO employeeDTO=employeeService.findById(employeeId);
			//model.addAttribute("employeeDTO", employeeDTO);
			//return "update";
			
		}
		
	
	@PostMapping("showeditEmployee")
	 String updateditEmployee(@ModelAttribute EmployeeDTO employeeDTO, Model model){//editProfileAction
		employeeService.updateditEmployee(employeeDTO);
			
	List<EmployeeDTO> listOfEmp=employeeService.giveAllEmp();
	
	model.addAttribute("listOfEmp", listOfEmp);
	
		return "showAll";//redirect:showAllEmployee
		
				
	}

	
	@GetMapping("register")
	String displayRegistrationPage(){
		
		return "registration";
		
	}
	


		@PostMapping("register")
	String doRegistration(@ModelAttribute EmployeeDTO empDTO){
		
		employeeService.saveEmp(empDTO);
		
		return "login";
		
	}



}
