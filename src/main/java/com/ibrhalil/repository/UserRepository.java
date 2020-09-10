package com.ibrhalil.repository;

import java.util.List;

import com.ibrhalil.models.User;

public interface UserRepository 
{
	User saveUser (User user);									//yeni kullanýcý ekleme arayüzü
	
	boolean saveUserProduct(int userId, int productId);			//user tablosu ile product tablosu arasýnda baðlantýyý eklemek için
	
	User updateUser(User user);									//user güncelleme
	
	boolean deleteUser(int userId);								//user silme
	
	User findUserById(int userId);								//id sahip user gösterme
	
	User findUserProductById(int userId);						//ürününe sahip kullanýcý
	
	List<User> findUsers();										//tüm kullanýcýlar
}
