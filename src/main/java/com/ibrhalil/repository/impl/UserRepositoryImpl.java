package com.ibrhalil.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.ibrhalil.connection.DBConnection;
import com.ibrhalil.models.Brand;
import com.ibrhalil.models.Category;
import com.ibrhalil.models.Product;
import com.ibrhalil.models.User;
import com.ibrhalil.models.queries.UserQueries;
import com.ibrhalil.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository
{
	
	private final Logger logger = (Logger) LogManager.getLogger();
	
	private Connection connection;
	
	private PreparedStatement preparedStatement;
	
	private ResultSet resultSet;

	@Override
	public User saveUser(User user) 
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			//INSERT INTO user(userid, firstname, lastname, birthofdate, username) VALUES(?,?,?,?,?)
			preparedStatement = connection.prepareStatement(UserQueries.saveUserQuery);
			
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setDate(4, user.getBirthOfDate());
			preparedStatement.setString(5, user.getUsename());
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			logger.warn("Yeni kullanýcý eklerken hata meydana geldi.\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return user;
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) 
	{
		connection = DBConnection.getConnection();
		try 
		{
			// INSERT INTO user_product(userid, productid) VALUES(?,?)
			preparedStatement = connection.prepareStatement(UserQueries.saveUser_ProductQuery);
			
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, productId);
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			logger.warn("user_product tablosuna ekleme yaparken hata meydana geldi.\n"+e);
			return false;
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}

	@Override
	public User updateUser(User user) 
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			//UPDATE user SET firstname = ?, lastname = ?, birthofdate = ?, username = ? WHERE userid = ?
			preparedStatement = connection.prepareStatement(UserQueries.updateUserQuery);
			
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, user.getBirthOfDate());
			preparedStatement.setString(4, user.getUsename());
			preparedStatement.setInt(5, user.getUserId());
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			logger.warn("Kullanýcý güncellenirken hata meydana geldi.\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return user;
	}

	@Override
	public boolean deleteUser(int userId) {
		connection = DBConnection.getConnection();
		
		try 
		{
			//user silinirken user tablosunun iliþkili olan tabloyu önce silinmeli
			preparedStatement = connection.prepareStatement(UserQueries.deleteUser_ProductByIdQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			
			//sonra user tablosundan silinir
			preparedStatement = connection.prepareStatement(UserQueries.deleteUserByIdQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			
			/*
			 * kendime not user silindiðinde, user 'a ait olan productlar silinmesi gerekmez mi ??
			 */
		} 
		catch (SQLException e) 
		{
			logger.warn("Kullanýcý silinirken hata meydana geldi.\n"+e);
			return false;
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}

	@Override
	public User findUserById(int userId) 
	{
		connection = DBConnection.getConnection();
		User user = null;
		try 
		{
			//SELECT * FROM user WHERE userid = ?
			preparedStatement = connection.prepareStatement(UserQueries.findUserByIdQuery);
			preparedStatement.setInt(1, userId);
			
			resultSet = preparedStatement.executeQuery();
			user = new User();
			
			while (resultSet.next()) 
			{
				user.setUserId(resultSet.getInt("userid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setBirthOfDate(resultSet.getDate(4));
				user.setUsename(resultSet.getString(5));
			}
			
		} 
		catch (SQLException e) 
		{
			logger.warn("Kullanýcý ararken hata meydana geldi.\n"+e);
		}
		finally 
		{
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
	}

	@Override
	public User findUserProductById(int userid) {
		connection = DBConnection.getConnection();
		User user = new User();
		List<Product> products = new ArrayList<Product>();
		boolean tekrar = true;
		
		try 
		{
			preparedStatement = connection.prepareStatement(UserQueries.findUserProductsQuery);
			preparedStatement.setInt(1, userid);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				if(tekrar) 
				{
					user.setUserId(resultSet.getInt("userid"));
					user.setFirstName(resultSet.getString("firstname"));
					user.setLastName(resultSet.getString("lastname"));
					user.setBirthOfDate(resultSet.getDate("birthofdate"));
					user.setUsename(resultSet.getString("username"));
					tekrar=false;
				}
				
				int productid = resultSet.getInt("productid");
				String productName = resultSet.getString("productname");
				double unitPrice = resultSet.getDouble("unitprice");
				int avaible = resultSet.getInt("avaible");
				Date addDate = resultSet.getDate("adddate");
				Date updateDate = resultSet.getDate("updatedate");
				
				int categoryid = resultSet.getInt("categoryid");
				String categoryName = resultSet.getString("categoryname");
				
				int brandid = resultSet.getInt("brandid");
				String brandName = resultSet.getString("brandname");
				
				Category category = new Category(categoryid, categoryName);
				Brand brand = new Brand(brandid, brandName);
				Product product = new Product(productid, productName, unitPrice, avaible, addDate, updateDate, category, brand); 
				
				products.add(product);
			}
			user.setProducts(products);
		} 
		catch (SQLException e) 
		{
			logger.warn("Kullanýcý ürünleri bulunurken bir hata meydana geldi.\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
	}

	@Override
	public List<User> findUsers() {
		List<User> userList =new ArrayList<User>();
		connection = DBConnection.getConnection();
		
		try 
		{
			preparedStatement = connection.prepareStatement(UserQueries.findUsersQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				User user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setBirthOfDate(resultSet.getDate("birthofdate"));
				user.setUsename(resultSet.getString("username"));
				userList.add(user);
			}
		} 
		catch (SQLException e) 
		{
			logger.warn("Kullanýcýlar bulunurken bir hata meydana geldi.\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}

		return userList;
	}

}
