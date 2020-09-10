package com.ibrhalil.service;

import java.util.List;

import com.ibrhalil.models.Product;

public interface ProductService 
{
	/*
	 * productService bizim frontend ile repository classlarýný soyutlamak amaçlý bir ara katmandýr.
	 */
	Product saveProduct(Product product);						
	
	boolean saveBatchProduct(List<Product> products);		
	
	Product updateProduct(Product product);						
	
	boolean removeProduct(int productid);						
	
	Product findProductById(int productid);					
	
	List<Product> findProducts();	
}
