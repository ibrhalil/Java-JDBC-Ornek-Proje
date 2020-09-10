package com.ibrhalil.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.ibrhalil.connection.DBConnection;
import com.ibrhalil.models.Category;
import com.ibrhalil.models.queries.CategoryQueries;
import com.ibrhalil.repository.CategoryRepository;

public class CategoryRepositoryImpl implements CategoryRepository
{
	private final Logger logger = (Logger) LogManager.getLogger();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	@Override
	public Category saveCategory(Category category)
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			//INSERT INTO category(categoryid, categoryname) VALUES(?,?)
			preparedStatement = connection.prepareStatement(CategoryQueries.saveCategoryQuery);
			preparedStatement.setInt(1, category.getCategoryid());
			preparedStatement.setString(2, category.getCategoryName());
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e)
		{
			logger.warn("yeni category eklenirken hata meydana geldi. !!!\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return category;
	}

	@Override
	public boolean deleteCategory(int id) 
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			//DELETE FROM category WHERE categoryid = ?
			preparedStatement = connection.prepareStatement(CategoryQueries.deleteCategoryByIdQuery);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			logger.warn(id+" id 'li category silinirken hata meydana geldi !!!\n"+e);
			return false;
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}

		return true;
	}

	@Override
	public Category findCategory(int id) 
	{
		connection = DBConnection.getConnection();
		Category category = new Category();
		try 
		{
			preparedStatement = connection.prepareStatement(CategoryQueries.findCategoryByIdQuery);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) 
			{
				category.setCategoryid(resultSet.getInt("categoryid"));
				category.setCategoryName(resultSet.getString("categoryname"));
			}
		}
		catch (SQLException e) 
		{
			logger.warn("category aranýrken hata meydana geldi!!!\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return category;
	}

	@Override
	public List<Category> findCategories() {
		connection = DBConnection.getConnection();
		List<Category> listCategories = new ArrayList<>();
		
		try 
		{
			preparedStatement = connection.prepareStatement(CategoryQueries.findCategorysQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) 
			{
				Category category = new Category();
				category.setCategoryid(resultSet.getInt("categoryid"));
				category.setCategoryName(resultSet.getString("categoryname"));
				
				listCategories.add(category);
			}
		}
		catch (SQLException e) 
		{
			logger.warn("category aranýrken hata meydana geldi!!!\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return listCategories;
	}

}
