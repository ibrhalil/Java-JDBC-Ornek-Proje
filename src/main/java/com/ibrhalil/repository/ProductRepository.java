package com.ibrhalil.repository;

import java.util.List;

import com.ibrhalil.models.Product;

public interface ProductRepository 
{
	Product saveProduct(Product product);						//yeni �r�n ekleme
	
	boolean saveBatchProduct(List<Product> products);			//toplu �ekilde �r�nleri eklemek
	
	Product updateProduct(Product product);						//�r�n g�ncelleme
	
	boolean removeProduct(int productid);						//�r�n silme
	
	Product findProductById(int productid);						//id g�re �r�n getirmek
	
	List<Product> findProducts();								//t�m �r�nleri listeleme
}
