package com.ibrhalil.service;

import java.util.List;

import com.ibrhalil.models.Product;

public interface ProductService 
{
	/*
	 * productService bizim frontend ile repository classlar�n� soyutlamak ama�l� bir ara katmand�r.
	 */
	Product saveProduct(Product product);						
	
	boolean saveBatchProduct(List<Product> products);		
	
	Product updateProduct(Product product);						
	
	boolean removeProduct(int productid);						
	
	Product findProductById(int productid);					
	
	List<Product> findProducts();	
}
