package com.bean;

import java.util.ArrayList;

public class EmployeeBean {

	int empId;
	String empName;
	int salary;
	
	ArrayList<SkillBean> skillBean = new ArrayList<>();
	
	public ArrayList<SkillBean> getSkillBean() {
		return skillBean;
	}
	public void setSkillBean(ArrayList<SkillBean> skillBean) {
		this.skillBean = skillBean;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
