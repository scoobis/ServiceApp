package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Service;

/**
 * A class that handles the Service Database.
 */
public class ServiceDatabase implements DatabaseConnector {
	
	Connection connection = DatabaseConnector.getConnection();

	/**
	 * Gets all Services from a certain company.
	 * @param companyName
	 * @return ArrayList<Service>
	 */
	
	public ArrayList<Service> getAllServices(String companyName) {
		ArrayList<Service> services = new ArrayList<>();
		
		try {
		String statement = "SELECT id FROM service WHERE company_name = '" + companyName + "'";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
	
		while(result.next()) {
			services.add(getServiceById(result.getInt("id")));
			
		}
		
		return services;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets a Service by ID.
	 * @param id
	 * @return Service
	 * @throws SQLException
	 */
	
	public Service getServiceById(int id) throws SQLException {
		String statement = "SELECT * FROM service WHERE id=" + id + ";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		Service s = new Service();
		
		while(result.next()) {
			s.setCompany(result.getString("company_name"));
			s.setTitle(result.getString("title"));
			s.setDescription(result.getString("description"));
			s.setPrice(result.getDouble("price"));
			s.setId(result.getInt("id"));
		}
		
		return s;
	}
	
	/**
	 * Saves a Service.
	 * @param s
	 * @return boolean
	 */
	
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
	
	/**
	 * Deletes a Service.
	 * @param id
	 * @return boolean
	 */
	
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
	
	/**
	 * Edits a Service
	 * @param s
	 * @return boolean
	 */
	
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
	
	// TEST METHOD
	
	public void reset() throws SQLException {
		String statement = "TRUNCATE TABLE service";
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate();

	}
}
