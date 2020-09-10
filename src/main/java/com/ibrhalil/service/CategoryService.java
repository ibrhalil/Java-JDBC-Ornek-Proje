package com.ibrhalil.service;

import java.util.List;

import com.ibrhalil.models.Category;

public interface CategoryService 
{
	Category saveCategory(Category category);
	
	boolean deleteCategory(int id);
	
	Category findCategory(int id);
	
	List<Category> findCategories();
}
