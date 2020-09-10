package com.ibrhalil.repository;

import java.util.List;

import com.ibrhalil.models.User;

public interface UserRepository 
{
	User saveUser (User user);									//yeni kullan�c� ekleme aray�z�
	
	boolean saveUserProduct(int userId, int productId);			//user tablosu ile product tablosu aras�nda ba�lant�y� eklemek i�in
	
	User updateUser(User user);									//user g�ncelleme
	
	boolean deleteUser(int userId);								//user silme
	
	User findUserById(int userId);								//id sahip user g�sterme
	
	User findUserProductById(int userId);						//�r�n�ne sahip kullan�c�
	
	List<User> findUsers();										//t�m kullan�c�lar
}
