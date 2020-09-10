package com.ibrhalil.models;

public class Brand 
{
	private int brandid;
	private String brandName;
	
	public Brand() 
	{
		
	}

	public Brand(int brandid, String brandName) 
	{
		this.brandid = brandid;
		this.brandName = brandName;
	}

	public int getBrandid() {
		return brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Override
	public String toString() {
		return "Brand [brandid=" + brandid + ", brandName=" + brandName + "]";
	}
}
