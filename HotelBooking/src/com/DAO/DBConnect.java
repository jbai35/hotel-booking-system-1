package com.DAO;

import java.sql.*;

public class DBConnect {
	
	//private final String PROP_FILE = "mysql.ini";
	private String driver = "com.mysql.jdbc.Driver";
	// url是数据库的服务地址
	private String url = "jdbc:mysql://localhost:3306/id26346990";
	private String user = "fit5183a1";
	private String pass = "";
	
	public Connection getConnection() throws Exception{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url
				, user , pass);
		return conn;
	}
	
}
