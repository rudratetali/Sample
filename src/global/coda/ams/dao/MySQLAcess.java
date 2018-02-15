package global.coda.ams.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import global.coda.ams.properties.TextPropertyReader;

public class MySQLAcess {
	private Connection connect = null;

	public Connection getConnection() {
		TextPropertyReader propertyReader=new TextPropertyReader();	
		
		// This will load the MySQL driver, each DB has its own driver
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		// Setup the connection with the DB
		try {
			String database=propertyReader.getDatabase();
			String username=propertyReader.getUser();
			String password=propertyReader.getPassword();
			
			
			connect = DriverManager.getConnection(database, username, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return connect;
	}

}
