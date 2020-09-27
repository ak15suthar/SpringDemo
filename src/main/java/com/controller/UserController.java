package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;
	
	//Signup
	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(UserBean userBean) {
		System.out.println("signup via post called...");
		System.out.println(userBean.getFirstName());
		System.out.println(userBean.getEmail());
		System.out.println(userBean.getPassword());
		
		userDao.insertUser(userBean);
		//return "UserAdded";
		//return userBean;
		//return new ResponseEntity<UserBean>(userBean,null,HttpStatus.CREATED);
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(userBean);
		responseBean.setMsg("Signup Done!!");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@GetMapping("/users")
	public ResponseBean<ArrayList<UserBean>> getUsers(){
		
		ResponseBean<ArrayList<UserBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(userDao.listUsers());
		responseBean.setMsg("List of Users");
		responseBean.setStatus(201);
		
		return responseBean;
	}
	
	@GetMapping("/user/{userId}")
	public ResponseBean<UserBean> getUser(@PathVariable("userId") int userId){
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userDao.getUserById(userId));
		responseBean.setMsg("Single data recevcied..");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseBean<UserBean> deleteUser(@PathVariable("userId") int userId){
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		System.out.println("Deleted");
		//responseBean.setData(userDao.deleteUserById(userId));
		responseBean.setMsg("User removed!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
