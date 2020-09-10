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
import com.ibrhalil.models.Brand;
import com.ibrhalil.models.queries.BrandQueries;
import com.ibrhalil.repository.BrandRepository;

public class BrandRepositoryImpl implements BrandRepository
{
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private final Logger logger = (Logger) LogManager.getLogger();
	
	@Override
	public Brand saveBrand(Brand brand) 
	{
		connection = DBConnection.getConnection();

		try 
		{
			//INSERT INTO brand(brandid, brandname) VALUES(?,?)
			preparedStatement = connection.prepareStatement(BrandQueries.saveBrandQuery);
			preparedStatement.setInt(1, brand.getBrandid());
			preparedStatement.setString(2, brand.getBrandName());
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			logger.warn("Brand eklenirken hata meydana geldi");
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		
		return brand;
	}
	@Override
	public boolean deleteBrand(int id) 
	{
		connection = DBConnection.getConnection();

		try 
		{
			preparedStatement = connection.prepareStatement(BrandQueries.deleteBrandByIdQuery);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			logger.warn("Brand silinirken hata meydana geldi");
			return false;
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}
	@Override
	public Brand findBrandyId(int id) 
	{
		connection = DBConnection.getConnection();

		try 
		{
			preparedStatement = connection.prepareStatement(BrandQueries.findBrandByIdQuery);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			Brand brand = new Brand();
			
			while (resultSet.next()) 
			{
				brand.setBrandid(resultSet.getInt("brandid"));
				brand.setBrandName(resultSet.getString("brandname"));
			}
		} 
		catch (SQLException e) 
		{
			logger.warn(id+" id'li Brand bulunurken hata meydana geldi!!!");
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return null;
	}
	@Override
	public List<Brand> findBrands()
	{
		connection = DBConnection.getConnection();
		List<Brand> listBrands = new ArrayList<>();

		try 
		{
			preparedStatement = connection.prepareStatement(BrandQueries.findBrandsQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			Brand brand = new Brand();
			
			while (resultSet.next()) 
			{
				brand.setBrandid(resultSet.getInt("brandid"));
				brand.setBrandName(resultSet.getString("brandname"));
				listBrands.add(brand);
			}
		} 
		catch (SQLException e) 
		{
			logger.warn("Brand listelenirken bir hata meydana geldi!!!");
			return null;
		}
		return listBrands;
	}
}
