package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observer;

import model.Customer;
import model.Service;

public class ServiceDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {
	
	Connection connection = DatabaseConnector.getConnection();

	public ArrayList<Service> getAllServices() throws SQLException {
		ArrayList<Service> services = new ArrayList<>();
		
		String statement = "SELECT id FROM service ";
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
	
	public void saveService(Service s) {
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
			
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public int deleteService(String id) {
		String statement = "DELETE FROM service WHERE id=" + id + ";";
		Statement query;
		try {
			query = connection.prepareStatement(statement);
			query.executeUpdate(statement);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void editService(String id, Service s) throws SQLException {
		PreparedStatement query;
		String statement = "UPDATE service SET " +
				"company_name = '" + s.getCompany() + "', " + 
				"title = '" + s.getTitle() + "', " + 
				"description = '" + s.getDescription() + "', " + 
				"price = " + s.getPrice() + 
				" WHERE id='" + id + "';";
		System.out.println(statement);
		query = connection.prepareStatement(statement);
		query.executeUpdate();
	}
	
	@Override
	public void attach(Observer o) {
		
	}

	@Override
	public void detach(Observer o) {
		
	}

	@Override
	public void update() {
		
	}

}
