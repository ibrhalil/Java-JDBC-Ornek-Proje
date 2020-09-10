package com.ibrhalil.service.impl;

import java.util.List;

import com.ibrhalil.models.Product;
import com.ibrhalil.repository.ProductRepository;
import com.ibrhalil.repository.impl.ProductRepositoryImpl;
import com.ibrhalil.service.ProductService;

public class ProductServiceImpl implements ProductService
{
	private ProductRepository productRepository = new  ProductRepositoryImpl();

	@Override
	public Product saveProduct(Product product) 
	{
		return productRepository.saveProduct(product);
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) 
	{
		return productRepository.saveBatchProduct(products);
	}

	@Override
	public Product updateProduct(Product product) 
	{
		return productRepository.updateProduct(product);
	}

	@Override
	public boolean removeProduct(int productid) 
	{
		return productRepository.removeProduct(productid);
	}

	@Override
	public Product findProductById(int productid) 
	{
		return productRepository.findProductById(productid);
	}

	@Override
	public List<Product> findProducts() 
	{
		return productRepository.findProducts();
	}
}
