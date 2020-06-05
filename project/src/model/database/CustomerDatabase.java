package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;

/**
 * A class that handles the Customer Database.
 */

public class CustomerDatabase implements DatabaseConnector {

	Connection connection = DatabaseConnector.getConnection();
	
	/**
	 * Gets all Customers from a certain company.
	 * @param companyName
	 * @return ArrayList<Customer>
	 */
	
	public ArrayList<Customer> getAllCustomers(String companyName) {
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		try {
		
		String statement = "SELECT * FROM customer WHERE company_name = '" + companyName + "'";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			Customer c = new Customer(result.getString("email"), result.getString("phone"),
					result.getString("name"), result.getString("adress"));
			c.setId(result.getInt("id"));
			
			customers.add(c);
		}
		
		return customers;
		
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets one Customer.
	 * @param id
	 * @return Customer
	 */
	
	public Customer getCustomerById(int id) {
		try {
		
		String statement = "SELECT * FROM customer WHERE id=" + id + ";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			Customer c = new Customer(result.getString("email"), result.getString("phone"),
					result.getString("name"), result.getString("adress"));
			c.setId(result.getInt("id"));
			
			return c;
		}
		
		} catch(SQLException e) { e.printStackTrace(); }
		return null;
	}
	
	/**
	 * Saves a Customer to the customer database.
	 * @param customer
	 * @return boolean
	 */
	
	public boolean saveCustomer(Customer customer) {
		try {
			PreparedStatement query;
			String statement = "INSERT INTO customer (phone, email, name, adress, company_name, status)"
						+ " VALUES ('" + customer.getPhone() + 
						"', '" + customer.getEmail() + 
						"', '" + customer.getName()+ 
						"', '" + customer.getAddress() +
						"', '" + customer.getCompany() +")";
			
			query = connection.prepareStatement(statement);
			query.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Deletes a customer.
	 * @param id
	 * @return boolean
	 */
	
	public boolean deleteCustomer(int id) {
		String statement = "DELETE FROM customer WHERE id=" + id + ";";
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
	 * Edits a customer.
	 * @param c The new customer.
	 * @return boolean
	 */
	public boolean editCustomer(Customer c) {
	
		try {
		String statement = "UPDATE customer SET phone = '" + c.getPhone() + "', " +
							"email = '" + c.getEmail() + "', " + 
							"name = '" + c.getName() + "', " + 
							"adress = '" + c.getAddress() + "'" + 
							" WHERE id='" + c.getId() + "';";
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate(statement);
		
		return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
