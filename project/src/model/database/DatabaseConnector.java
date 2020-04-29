package model.database;

import java.sql.Connection;

public interface DatabaseConnector {

	public String serverName = "";
	public String instanceName = "";
	public String username = "";
	public String password = "";
	public int port = -1;
	public Connection database = null; //TODO Make sure this is correct connection object
}
