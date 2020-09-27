package com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

 	public static ArrayList<UserBean> users = new ArrayList<>();
 	
 	public void insertUser(UserBean userBean) {
 		users.add(userBean);
 	}
 	
 	public ArrayList<UserBean> listUsers(){
 		return users;
 	}
 	
 	public UserBean getUserById(int userId) {
 		UserBean userBean = null;
 		
 		for(UserBean user:users) {
 			if(user.getUserId() == userId) {
 				userBean=user;
 				break;
 			}
 		}
 		return userBean;	
 	}
 	
 	public boolean deleteUserById(int userId) {
 		UserBean userBean = null;
 		
 		for(UserBean user:users) {
 			if(user.getUserId() == userId) {
 				userBean=user;
 				break;
 			}
 		}
 		
 		users.remove(userBean);
 		
 		return true;
 	}
}
