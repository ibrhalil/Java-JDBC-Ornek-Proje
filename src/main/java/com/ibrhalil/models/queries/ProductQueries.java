package com.ibrhalil.models.queries;

public class ProductQueries 
{
	public static final String saveProductQuery = "INSERT INTO product(productid, productname, unitprice, avaible, adddate, updatedate, categoryid, brandid) VALUES(?,?,?,?,?,?,?,?)";
	
	public static final String updateProductQuery = "UPDATE product SET productname = ?, unitprice = ?, avaible = ?, updatedate = ?, categoryid = ?, brandid = ? WHERE productid = ?";

	public static final String deleteUser_ProductQuery = "DELETE FROM user_product WHERE productid = ?";	//iliþkili tablodan bir nesne silmek için önce iliþkide olan veriyi silmek gerekmektedir.
	public static final String deleteProductQuery = "DELETE FROM product WHERE productid = ?";
	
	public static final String findProductByIdQuery = "SELECT * FROM product p LEFT JOIN category c ON(p.categoryid = c.categoryid) LEFT JOIN brand b ON(p.brandid = b.brandid) WHERE productid = ?";
	
	public static final String findProducts = "SELECT * FROM product p LEFT JOIN category c ON(p.categoryid = c.categoryid) LEFT JOIN brand b ON(p.brandid = b.brandid)";
	
}
