package com.halloween.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoping", "root", "");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Connection getConSQL() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433; databaseName=SHOPING";
		Connection con = DriverManager.getConnection(url, "sa", "1");
		return con;
	}
}
