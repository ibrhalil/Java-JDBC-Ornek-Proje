package com.ibrhalil.models;

public class Category 
{
	private int categoryid;
	private String categoryName;
	
	public Category() 
	{
		
	}

	public Category(int categoryid, String categoryName) 
	{
		this.categoryid = categoryid;
		this.categoryName = categoryName;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryName=" + categoryName + "]";
	}
}
