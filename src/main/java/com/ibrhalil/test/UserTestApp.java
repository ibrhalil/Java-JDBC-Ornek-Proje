package com.ibrhalil.test;

import java.sql.Date;
import java.util.List;

import com.ibrhalil.models.Product;
import com.ibrhalil.models.User;
import com.ibrhalil.service.UserService;
import com.ibrhalil.service.impl.UserServiceImpl;

public class UserTestApp {

	public static void main(String[] args) 
	{
		UserService userService = new UserServiceImpl();
		
		//Tüm user'lar
		/*
		List<User> listUser = userService.findUsers();
		for (User user : listUser) {
			System.out.println(user);
		}
		*/
		
		
		//userid = 3
		/*
		User userId3 = userService.findUserById(3);
		System.out.println(userId3);
		*/
		
		
		//user silmek (Önemli Not = user silindiðinde user_product tablosundan da ürünler iliþkisini silinmelidir.)
		/*
		boolean silindiMi = userService.deleteUser(4);
		if (silindiMi) {
			System.out.println("userid 4 olan kiþi silindi.");
		}
		//ayný zamanda user_product tablosundan da silinmeli
		*/
		
		
		//kullanýcýnýn tüm ürünlerini listelemek
		/*
		User userProducts = userService.findUserProductById(2);
		List<Product> urunler = userProducts.getProducts();
		System.out.println(userProducts+" kullanýcýnýn ürünleri ;");
		for (Product user_product : urunler) {
			System.out.println("\t"+user_product);
		}
		*/
		
		//kullanýcý güncelleme
		/*
		User degisUser = userService.findUserById(3);
		degisUser.setFirstName("Ahmet");
		degisUser.setLastName("Karaman");
		degisUser.setBirthOfDate(new Date(1990, 5, 13));
		degisUser.setUsename("@AhmetK");
		User guncelUser = userService.updateUser(degisUser);
		System.out.println(guncelUser+" Güncellendi.");
		*/
		
		/*
		User yeniUser = new User(4,"Yusuf", "Turhan", new Date(2005, 9, 5), "@yusuf");
		userService.saveUser(yeniUser);
		*/
		
	}

}
