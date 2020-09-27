package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import com.bean.EmployeeBean;
import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.EmployeeDao;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@PostMapping("/employeeAdd")
	public ResponseBean<EmployeeBean> addEmployee(@RequestBody EmployeeBean employeeBean){
		
		ResponseBean<EmployeeBean> responseBean = new ResponseBean<>();
		
		int empId =  employeeDao.insertEmployee(employeeBean);
		
		for(SkillBean sb:employeeBean.getSkillBean()) {
		
			employeeDao.addSkillForEmployee(empId, sb.getSkillId());
			
			System.out.println(sb.getSkillId());
			System.out.println(sb.getSkillName());
		}
		
		employeeBean.setEmpId(empId);
		
		responseBean.setData(employeeBean);
		responseBean.setMsg("Employee Inserted!!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@GetMapping("/employeeList")
	public ResponseBean<List<EmployeeBean>> listEmployee(){
		
		ResponseBean<List<EmployeeBean>> responseBean = new ResponseBean<>();
		
		List<EmployeeBean> employeeBean = employeeDao.listEmployee();
		responseBean.setData(employeeBean);
		responseBean.setMsg("Employee List!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@PutMapping("/employeeUpdate")
	public ResponseBean<EmployeeBean> updateEmployee(EmployeeBean employeeBean){
		
		employeeDao.updateEmployee(employeeBean);
		
		ResponseBean<EmployeeBean> responseBean = new ResponseBean<>();
		responseBean.setData(employeeBean);
		responseBean.setMsg("Employee Updated!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
	
	@DeleteMapping("/employeeDelete/{empid}")
	public ResponseBean<EmployeeBean> deleteEmployee(@PathVariable("empid") int empid){
		
		employeeDao.deleteEmployee(empid);
		
		ResponseBean<EmployeeBean> responseBean = new ResponseBean<>();
		responseBean.setMsg("Deleted Employee!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
