package com.ibrhalil.service.impl;

import java.util.List;

import com.ibrhalil.models.User;
import com.ibrhalil.repository.UserRepository;
import com.ibrhalil.repository.impl.UserRepositoryImpl;
import com.ibrhalil.service.UserService;

public class UserServiceImpl implements UserService 
{
	private UserRepository userRepository = new UserRepositoryImpl();

	@Override
	public User saveUser(User user) 
	{
		return userRepository.saveUser(user);
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) 
	{
		return userRepository.saveUserProduct(userId, productId);
	}

	@Override
	public User updateUser(User user) 
	{
		return userRepository.updateUser(user);
	}

	@Override
	public boolean deleteUser(int userId) 
	{
		return userRepository.deleteUser(userId);
	}

	@Override
	public User findUserById(int userId) 
	{
		return userRepository.findUserById(userId);
	}

	@Override
	public User findUserProductById(int userId)
	{
		return userRepository.findUserProductById(userId);
	}

	@Override
	public List<User> findUsers() 
	{
		return userRepository.findUsers();
	}
}
