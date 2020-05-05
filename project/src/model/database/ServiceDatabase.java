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
		
		String statement = "SELECT id FROM services ";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		int i = 0;
		while(result.next()) {
			services.add(getServiceById(result.getString(i)));
			i++;
		}
		
		return services;
	}
	
	public Service getServiceById(String id) throws SQLException {
		String statement = "SELECT * FROM service WHERE id=" + id + ";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		int i = 0;
		
		while(result.next()) {
			// Create a Customer here.
			System.out.println(result.getString(i));
			i++;
		}
		
		Service fetchedService = new Service();
		
		return fetchedService;
	}
	
	public void saveService(Service s) {
		try {
			
			PreparedStatement query;
			query = connection.prepareStatement("INSERT INTO service (company_name, title, description, price)"
						+ "VALUES ('"+ s.getCompany() +"', "
						+ s.getTitle()+","
						+ s.getDescription()+","
						+ "'"+ s.getPrice() +"')");
			query.executeUpdate();
			
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public int deleteService(String id) {
		String statement = "DELETE FROM service WHERE id=" + id + ";";
		Statement query;
		try {
			query = connection.prepareStatement(statement);
			query.executeQuery(statement);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Service editService(String id, Service s) {
		deleteService(id);
		saveService(s);
		
		return s;
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
