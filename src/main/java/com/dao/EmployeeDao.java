package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.EmployeeBean;
import com.bean.SkillBean;

@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate stmt;

	public int insertEmployee(EmployeeBean employeeBean) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		// stmt.update("insert into employee(ename,salary) values(?,?)", employeeBean.getEmpName(),employeeBean.getSalary(),Statement.RETURN_GENERATED_KEYS);
		String insertQ = "insert into employee(ename,salary) values(?,?)";

		stmt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(insertQ,Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, employeeBean.getEmpName());
				pstmt.setInt(2, employeeBean.getSalary());
		
				return pstmt;
			}
		},keyHolder);
		
		int empId = (Integer) keyHolder.getKeys().get("empid");
		return empId;
	}

	public void addSkillForEmployee(int empId,int skillId) {
		stmt.update("insert into empskill (empid,skillid) values (?,?)",empId,skillId);
	}
	
	
	public List<EmployeeBean> listEmployee() {

		List<EmployeeBean> employeeBean = stmt.query("select * from employee",
				BeanPropertyRowMapper.newInstance(EmployeeBean.class));

		return employeeBean;
	}

	public void updateEmployee(EmployeeBean employeeBean) {

		stmt.update("update employee set ename = ? , salary = ? where empid = ?", employeeBean.getEmpName(),
				employeeBean.getSalary(), employeeBean.getEmpId());

	}

	public void deleteEmployee(int empid) {
		stmt.update("delete from employee where empid = ?", empid);
	}
}
