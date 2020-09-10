package com.ibrhalil.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.ibrhalil.connection.DBConnection;
import com.ibrhalil.models.Brand;
import com.ibrhalil.models.Category;
import com.ibrhalil.models.Product;
import com.ibrhalil.models.queries.ProductQueries;
import com.ibrhalil.repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository
{
	private final Logger logger = (Logger) LogManager.getLogger();
	
	private Connection connection = null;
	
	private PreparedStatement preparedStatement = null;
	
	private ResultSet resultSet = null;
	
	@Override
	public Product saveProduct(Product product) 
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			//INSERT INTO product(productid, productname, unitprice, avaible, adddate, updatedate, categoryid, brandid) VALUES(?,?,?,?,?,?,?,?)
			preparedStatement = connection.prepareStatement(ProductQueries.saveProductQuery);
			preparedStatement.setInt(1, product.getProductid());
			preparedStatement.setString(2, product.getProductName());
			preparedStatement.setDouble(3, product.getUnitPrice());
			preparedStatement.setInt(4, product.getAvaible());
			preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));//ekleme tarihi þuan
			preparedStatement.setDate(6, null);		//ekleme zamanýnda güncelleme yapýlmaz

			//Category veya Brand nesneleri Product nesnesine tanýmlanmamýþ ise hata meydana gelmektedir.
			if(product.getCategory()!=null)
			{
				preparedStatement.setInt(7, product.getCategory().getCategoryid());
			}
			else
			{
				throw new SQLException("Product nesnesine Category nesnesi tanýmlanmamýþtýr !!!");
			}
			
			if(product.getBrand()!=null)
			{
				preparedStatement.setInt(8, product.getBrand().getBrandid());
			}
			else
			{
				throw new SQLException("Product nesnesine Brand nesnesi tanýmlanmamýþtýr !!!");
			}
			preparedStatement.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			logger.warn("ürün eklenirken bir hata meydana geldi.\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return product;
	}

	@Override
	public boolean saveBatchProduct(List<Product> products) 
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			preparedStatement = connection.prepareStatement(ProductQueries.saveProductQuery);
			for (Product product : products) 
			{
				
				preparedStatement.setInt(1, product.getProductid());
				preparedStatement.setString(2, product.getProductName());
				preparedStatement.setDouble(3, product.getUnitPrice());
				preparedStatement.setInt(4, product.getAvaible());
				preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));//ekleme tarihi þuan
				preparedStatement.setDate(6, null);		//ekleme zamanýnda güncelleme yapýlmaz

				//Category veya Brand nesneleri Product nesnesine tanýmlanmamýþ ise hata meydana gelmektedir.
				if(product.getCategory()!=null)
				{
					preparedStatement.setInt(7, product.getCategory().getCategoryid());
				}
				else
				{
					throw new SQLException("Product nesnesine Category nesnesi tanýmlanmamýþtýr !!!");
				}
				
				if(product.getBrand()!=null)
				{
					preparedStatement.setInt(8, product.getBrand().getBrandid());
				}
				else
				{
					throw new SQLException("Product nesnesine Brand nesnesi tanýmlanmamýþtýr !!!");
				}
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			
		} 
		catch (SQLException e) 
		{
			logger.warn("liste halinde product nesnesi eklenirken hata meydana geldi.\n"+e);
			return false;
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return true;
	}

	@Override
	public Product updateProduct(Product product) 
	{
		connection = DBConnection.getConnection();
		
		try 
		{
			//UPDATE product SET productname = ?, unitprice = ?, avaible = ?, updatedate = ?, categoryid = ?, brandid = ? WHERE productid = ?
			preparedStatement = connection.prepareStatement(ProductQueries.updateProductQuery);
			
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setDouble(2, product.getUnitPrice());
			preparedStatement.setInt(3, product.getAvaible());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			preparedStatement.setInt(5, product.getCategory().getCategoryid());
			preparedStatement.setInt(6, product.getBrand().getBrandid());
			
			preparedStatement.setInt(7, product.getProductid());
			
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			logger.warn(product.getProductid()+" id ürün güncellenirken hata meydana geldi.\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, null);
		}
		return product;
	}

	@Override
	public boolean removeProduct(int productid) 
	{
		/*
		 * ürün silinmeden önce user_product tablosu arasýndaki iliþki silinmelidir.
		 */
		connection = DBConnection.getConnection();
		
		try 
		{
			preparedStatement = connection.prepareStatement(ProductQueries.deleteUser_ProductQuery);
			preparedStatement.setInt(1, productid);
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement(ProductQueries.deleteProductQuery);
			preparedStatement.setInt(1, productid);
			preparedStatement.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			logger.warn("Ürün silinirken hata meydana geldi !!!\n"+e);
			return false;
		}
		
		return true;
	}

	@Override
	public Product findProductById(int productid) 
	{
		connection = DBConnection.getConnection();
		Category category = null;
		Brand brand = null;
		Product product = null;
		// SELECT * FROM product p LEFT JOIN category c ON(p.categoryid = c.categoryid) LEFT JOIN brand b ON(p.brandid = b.brandid) WHERE productid = ?
		try 
		{
			preparedStatement = connection.prepareStatement(ProductQueries.findProductByIdQuery);
			preparedStatement.setInt(1, productid);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				category = new Category(resultSet.getInt("categoryid"), resultSet.getString("categoryname"));
				brand = new Brand(resultSet.getInt("brandid"), resultSet.getString("brandname"));
				product = new Product(resultSet.getInt("productid"), resultSet.getString("productname"), resultSet.getDouble("unitprice"), resultSet.getInt("avaible"), resultSet.getDate("adddate"), resultSet.getDate("updatedate"), category, brand);
				
			}
		} 
		catch (SQLException e) 
		{
			logger.warn("ürün aranýrken hata meydana geldi !!! \n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return product;
	}

	@Override
	public List<Product> findProducts() {
		connection = DBConnection.getConnection();
		List<Product> listProdoct = new ArrayList<>();
		
		try 
		{
			preparedStatement = connection.prepareStatement(ProductQueries.findProducts);
			
			resultSet = preparedStatement.executeQuery();
			
			Category category = null;
			Brand brand = null;
			Product product = null;
			
			while (resultSet.next()) 
			{
				int categoryid = resultSet.getInt("categoryid");
				String categoryName = resultSet.getString("categoryname");
				
				category = new Category(categoryid, categoryName);
				
				int brandid = resultSet.getInt("brandid");
				String brandName = resultSet.getString("brandname");
				
				brand = new Brand(brandid, brandName);
				
				int productid = resultSet.getInt("productid");
				String productName = resultSet.getString("productname");
				double unitPrice = resultSet.getDouble("unitprice");
				int avaible = resultSet.getInt("avaible");
				Date addDate = resultSet.getDate("adddate");
				Date updateDate = resultSet.getDate("updatedate");
				
				product = new Product(productid, productName, unitPrice, avaible, addDate, updateDate, category, brand);
			
				listProdoct.add(product);
			}
			
		} 
		catch (SQLException e) 
		{
			logger.warn("ürünler listelenirken bir hata meydana geldi !!!\n"+e);
		}
		finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		return listProdoct;
	}

}
