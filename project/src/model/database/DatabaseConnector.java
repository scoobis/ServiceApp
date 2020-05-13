package model.database;

import java.sql.Connection;
import java.sql.DriverManager;

import model.database.secretStuff.DatabaseSecrets;

public interface DatabaseConnector {

DatabaseSecrets dbSercrets = new DatabaseSecrets();
	
	final String driver = "com.mysql.jdbc.Driver";
	final String url = dbSercrets.getUrl();
	final String user = dbSercrets.getUsername();
	final String password = dbSercrets.getPassword();

	public static Connection getConnection() {
		try {
            Class.forName(driver);
            Connection mysqlConnection = DriverManager.getConnection(url, user, password);
            return mysqlConnection;
            
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
		
	}
}
