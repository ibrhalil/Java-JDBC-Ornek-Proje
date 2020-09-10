package com.ibrhalil.service.impl;

import java.util.List;

import com.ibrhalil.models.Category;
import com.ibrhalil.repository.CategoryRepository;
import com.ibrhalil.repository.impl.CategoryRepositoryImpl;
import com.ibrhalil.service.CategoryService;

public class CategoryServiceImpl implements CategoryService
{
	private CategoryRepository categoryRepository = new CategoryRepositoryImpl();

	@Override
	public Category saveCategory(Category category) 
	{
		return categoryRepository.saveCategory(category);
	}

	@Override
	public boolean deleteCategory(int id) 
	{
		return categoryRepository.deleteCategory(id);
	}

	@Override
	public Category findCategory(int id) 
	{
		return categoryRepository.findCategory(id);
	}

	@Override
	public List<Category> findCategories() 
	{
		return categoryRepository.findCategories();
	}
}
