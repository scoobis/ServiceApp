package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Service;

public class ServiceDatabase implements DatabaseConnector {
	
	Connection connection = DatabaseConnector.getConnection();

	public ArrayList<Service> getAllServices(String companyName) throws SQLException {
		ArrayList<Service> services = new ArrayList<>();
		
		String statement = "SELECT id FROM service WHER company_name = " + companyName;
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
	
		while(result.next()) {
			services.add(getServiceById(result.getString("id")));
			
		}
		
		return services;
	}
	
	public Service getServiceById(String id) throws SQLException {
		String statement = "SELECT * FROM service WHERE id=" + id + ";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		Service s = new Service();
		
		while(result.next()) {
			s.setCompany(result.getString("company_name"));
			s.setTitle(result.getString("title"));
			s.setDescription(result.getString("description"));
			s.setPrice(result.getInt("price"));
		}
		
		return s;
	}
	
	public boolean saveService(Service s) {
		try {
			
			PreparedStatement query;
			String statement = "INSERT INTO service (company_name, title, description, price)"
					+ " VALUES ('" + s.getCompany() + 
					"', '" + s.getTitle() + 
					"', '" + s.getDescription()+ 
					"', " + s.getPrice() + 
					")";
			
			query = connection.prepareStatement(statement);
			query.executeUpdate();
			return true;
			
		} catch (SQLException e1) { 
			e1.printStackTrace();
			return false;
			}
	}
	
	public boolean deleteService(int id) {
		String statement = "DELETE FROM service WHERE id=" + id + ";";
		Statement query;
		try {
			query = connection.prepareStatement(statement);
			query.executeUpdate(statement);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean editService(Service s) {
		
		try {
		PreparedStatement query;
		String statement = "UPDATE service SET " +
				"company_name = '" + s.getCompany() + "', " + 
				"title = '" + s.getTitle() + "', " + 
				"description = '" + s.getDescription() + "', " + 
				"price = " + s.getPrice() + 
				" WHERE id='" + s.getId() + "';";
		System.out.println(statement);
		query = connection.prepareStatement(statement);
		query.executeUpdate();
		
		return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void reset() throws SQLException {
		String statement = "TRUNCATE TABLE service";
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate();

	}
}
