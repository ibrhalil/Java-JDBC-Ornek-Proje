package com.ibrhalil.repository;

import java.util.List;

import com.ibrhalil.models.Brand;

public interface BrandRepository 
{
	Brand saveBrand(Brand brand);
	
	boolean deleteBrand(int id);
	
	Brand findBrandyId(int id);
	
	List<Brand> findBrands();
}
