package com.ibrhalil.models.queries;

public class CategoryQueries 
{
	public static final String saveCategoryQuery = "INSERT INTO category(categoryid, categoryname) VALUES(?,?)";
	
	public static final String findCategoryByIdQuery = "SELECT * FROM category WHERE categoryid = ?";
	
	public static final String findCategorysQuery = "SELECT * FROM category";
	
	public static final String deleteCategoryByIdQuery = "DELETE FROM category WHERE categoryid = ?";
}
