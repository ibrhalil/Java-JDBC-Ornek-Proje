package com.ibrhalil.service.impl;

import java.util.List;

import com.ibrhalil.models.Brand;
import com.ibrhalil.repository.BrandRepository;
import com.ibrhalil.repository.impl.BrandRepositoryImpl;
import com.ibrhalil.service.BrandService;

public class BrandServiceImpl implements BrandService
{
	private BrandRepository brandRepo = new BrandRepositoryImpl();
	
	@Override
	public Brand saveBrand(Brand brand)
	{
		return brandRepo.saveBrand(brand);
	}

	@Override
	public boolean deleteBrand(int id) 
	{
		return brandRepo.deleteBrand(id);
	}

	@Override
	public Brand findBrandyId(int id)
	{
		return brandRepo.findBrandyId(id);
	}

	@Override
	public List<Brand> findBrands() 
	{
		return brandRepo.findBrands();
	}

}
