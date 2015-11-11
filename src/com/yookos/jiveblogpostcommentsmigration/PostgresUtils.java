package com.yookos.jiveblogpostcommentsmigration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresUtils {

	private static Connection connection;
	private static Statement statement;
	
	public static boolean isPostgresDriverLoaded() {
		
		try {
			System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
			Class.forName("org.postgresql.Driver");
			System.out.println("PostgreSQL JDBC Driver Registered!");
			System.out.println();
			return true;
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return false;
		}
	}
    
    public static Connection openPostgresConnection(String url, String username, String password) {
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} 
		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closePostgresConnection() {
		
		try {
	        statement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
