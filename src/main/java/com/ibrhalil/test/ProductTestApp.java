package com.ibrhalil.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ibrhalil.models.Brand;
import com.ibrhalil.models.Category;
import com.ibrhalil.models.Product;
import com.ibrhalil.service.ProductService;
import com.ibrhalil.service.UserService;
import com.ibrhalil.service.impl.ProductServiceImpl;
import com.ibrhalil.service.impl.UserServiceImpl;

public class ProductTestApp 
{
	public static void main(String[] args) 
	{
		
		ProductService productService = new ProductServiceImpl();
		
		/*
		List<Product> listProduct = productService.findProducts();
		for (Product product : listProduct) {
			System.out.println(product);
		}*/
		
		
		/*
		Product produc = productService.findProductById(7);
		System.out.println(produc);
		*/
		
		
		/*
		Category category = new Category();
		category.setCategoryid(2);
		
		Brand brand = new Brand();
		brand.setBrandid(102);
		
		Product product = 
		new Product(7, "televizyon", 3000, 3, new Date(1900, 6, 20), null, category, brand);
		productService.saveProduct(product);
		*/
		
		
		
		//bir ürün eklendiðinde eklenyen user ile iliþkilendirilmelidir.(7 numaralý ürünü 4 nmaralý kullanýcýyla iþliþkilendirme) 
		/*
		UserService uService = new UserServiceImpl();
		uService.saveUserProduct(4, 7);
		*/
		
		
		//toplu ürün ekleme 
		List<Product> listUrunler = new ArrayList<>();
		
		Category category = new Category();
		Brand brand = new Brand();
		
		category.setCategoryid(3);
		brand.setBrandid(102);
		
		listUrunler.add(new Product(4, "test44", 11, 20, null, null, category, brand));
		
		
		category.setCategoryid(1);
		brand.setBrandid(101);
		
		listUrunler.add(new Product(5, "test45", 322, 0, null, null, category, brand));
		
		category.setCategoryid(4);
		brand.setBrandid(100);
		
		listUrunler.add(new Product(6, "test46", 256, 365, null, null, category, brand));
		productService.saveBatchProduct(listUrunler);
		
	}
}
