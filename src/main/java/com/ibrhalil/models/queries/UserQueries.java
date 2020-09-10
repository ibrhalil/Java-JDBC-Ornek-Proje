package com.ibrhalil.models.queries;

public class UserQueries 
{
	public static final String saveUserQuery = "INSERT INTO user(userid, firstname, lastname, birthofdate, username) VALUES(?,?,?,?,?)";
	public static final String saveUser_ProductQuery = "INSERT INTO user_product(userid, productid) VALUES(?,?)";	//iliþkide olan tabloda her ürün eklendiðinde ekleyen kullanýcýyý tabloda belirtmeliyiz.
	
	public static final String updateUserQuery = "UPDATE user SET firstname = ?, lastname = ?, birthofdate = ?, username = ? WHERE userid = ?";
	
	public static final String deleteUser_ProductByIdQuery = "DELETE FROM user_product WHERE userid = ?";
	public static final String deleteUserByIdQuery = "DELETE FROM user WHERE userid = ?";
	
	public static final String findUserByIdQuery = "SELECT * FROM user WHERE userid = ?";
	
	public static final String findUsersQuery = "SELECT * FROM user";
	
	public static final String findUserProductsQuery = "SELECT * FROM user u " + 
						"LEFT OUTER JOIN user_product up ON(u.userid = up.userid) " + 
						"LEFT JOIN product p ON(up.productid = p.productid) " + 
						"LEFT JOIN category c ON(p.categoryid = c.categoryid) " + 
						"LEFT JOIN brand b ON(p.brandid = b.brandid) " + 
						"WHERE u.userid = ?";
	/*
	 * userid ile kullanýcýya ait olan tüm ürünleri ve iliþkili tüm tablolarýný isteriz
	 */
}
