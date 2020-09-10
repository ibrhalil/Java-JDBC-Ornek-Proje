package com.ibrhalil.service;

import java.util.List;

import com.ibrhalil.models.User;

public interface UserService 
{
	User saveUser (User user);									
	
	boolean saveUserProduct(int userId, int productId);			
	
	User updateUser(User user);									
	
	boolean deleteUser(int userId);							
	
	User findUserById(int userId);							
	
	User findUserProductById(int userId);					
	
	List<User> findUsers();		
}
