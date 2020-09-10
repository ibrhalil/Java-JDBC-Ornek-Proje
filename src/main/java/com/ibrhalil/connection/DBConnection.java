package com.ibrhalil.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class DBConnection 
{
	private static Connection connection = null;
	private static final Logger LOGGER = (Logger) LogManager.getLogger();
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static{
		
		Properties properties = new Properties();
		try 
		{
			InputStream stream = new FileInputStream("src/main/resources/database.properties");
			properties.load(stream);
			
			driver = properties.getProperty("db_driver");
			url = properties.getProperty("db_url");
			user = properties.getProperty("db_user");
			password = properties.getProperty("db_password");
		} 
		catch (IOException e) 
		{
			LOGGER.warn("properties nesnesi oluþturulurken hata meydana geldi. \n"+e);
		}	
	}
	
	public static Connection getConnection() 
	{
		try 
		{
			Class.forName(driver);
		} 
		catch (ClassNotFoundException e) 
		{
			LOGGER.warn("Eksik Class bulunmakta !!!");
		}
		
		try 
		{
			connection = DriverManager.getConnection(url,user,password);
		} 
		catch (SQLException e) 
		{
			LOGGER.warn("Baðlantý kurulamadý !!!\n"+e);
		}
		
		return connection;
	}
	
	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

		try 
		{
			if(resultSet!=null)
				resultSet.close();
			
			preparedStatement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			LOGGER.warn("Baðlantý Güvenli kapatýlamadý !!!\n"+e);
		}
		

	}
}
