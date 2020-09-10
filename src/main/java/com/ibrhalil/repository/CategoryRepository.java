package com.ibrhalil.repository;

import java.util.List;

import com.ibrhalil.models.Category;

public interface CategoryRepository 
{
	Category saveCategory(Category category);
	
	boolean deleteCategory(int id);
	
	Category findCategory(int id);
	
	List<Category> findCategories();
	
}
