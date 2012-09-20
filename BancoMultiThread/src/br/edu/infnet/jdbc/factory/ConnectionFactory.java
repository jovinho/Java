package br.edu.infnet.jdbc.factory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*String url = "jdbc:mysql://localhost:3306/aula";*/
			String url = "jdbc:mysql://localhost:3306/banco";
			conn = DriverManager.getConnection(url, "root", "");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
