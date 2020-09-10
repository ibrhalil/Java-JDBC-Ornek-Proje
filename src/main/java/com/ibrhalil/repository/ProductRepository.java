package com.ibrhalil.repository;

import java.util.List;

import com.ibrhalil.models.Product;

public interface ProductRepository 
{
	Product saveProduct(Product product);						//yeni ürün ekleme
	
	boolean saveBatchProduct(List<Product> products);			//toplu þekilde ürünleri eklemek
	
	Product updateProduct(Product product);						//ürün güncelleme
	
	boolean removeProduct(int productid);						//ürün silme
	
	Product findProductById(int productid);						//id göre ürün getirmek
	
	List<Product> findProducts();								//tüm ürünleri listeleme
}
