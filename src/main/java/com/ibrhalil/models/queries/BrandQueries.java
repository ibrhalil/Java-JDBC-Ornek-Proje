package com.ibrhalil.models.queries;

public class BrandQueries 
{
	public static final String saveBrandQuery = "INSERT INTO brand(brandid, brandname) VALUES(?,?)";
	
	public static final String findBrandByIdQuery = "SELECT * FROM brand WHERE brandid = ?";
	
	public static final String findBrandsQuery = "SELECT * FROM brand";
	
	public static final String deleteBrandByIdQuery = "DELETE FROM brand WHERE brandid = ?";
}
