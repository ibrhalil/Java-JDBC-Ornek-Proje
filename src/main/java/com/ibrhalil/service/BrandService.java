package com.ibrhalil.service;

import java.util.List;

import com.ibrhalil.models.Brand;

public interface BrandService 
{
	Brand saveBrand(Brand brand);
	
	boolean deleteBrand(int id);
	
	Brand findBrandyId(int id);
	
	List<Brand> findBrands();
}
