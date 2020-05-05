package model.database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observer;

import model.Customer;

public class CustomerDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {

	Connection connection = DatabaseConnector.getConnection();
	
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		String statement = "SELECT id FROM customer ";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			customers.add(getCustomerById(result.getString("id")));
		}
		
		return customers;
	}
	
	public Customer getCustomerById(String id) throws SQLException {
		
		String statement = "SELECT * FROM customer WHERE id=" + id + ";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		int i = 0;
		
		Customer c = new Customer();
		
		while(result.next()) {
			c.setActive(result.getBoolean("isActive"));
			c.setAddress(result.getString("adress"));
			c.setEmail(result.getString("email"));
			c.setName(result.getString("name"));
			c.setPhone(result.getString("phone"));
		}
		
		return c;
	}
	
	public void saveCustomer(Customer customer) throws InvalidKeySpecException {
		try {
			
			PreparedStatement query;
			String statement = "INSERT INTO customer (phone, email, name, adress, isActive)"
						+ " VALUES ('" + customer.getPhone() + 
						"', '" + customer.getEmail() + 
						"', '" + customer.getName()+ 
						"', '" + customer.getAddress() + 
						"'," +  customer.isActive() +")";
			
			System.out.println(statement);
			query = connection.prepareStatement(statement);
			query.executeUpdate();
			
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	// TODO
	// * Use TRUNCATE TABLE instead of DELETE, this will update the ids which makes it easier to test.
	public int deleteCustomer(String id) {
		String statement = "DELETE FROM customer WHERE id=" + id + ";";
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
	
	public void editCustomer(String id, Customer c) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
		
		String statement = "UPDATE customer SET phone = '" + c.getPhone() + "', " +
							"email = '" + c.getEmail() + "', " + 
							"name = '" + c.getName() + "', " + 
							"adress = '" + c.getAddress() + "', " + 
							"isActive = " + c.isActive() + 
							" WHERE id='" + id + "';";
		System.out.println(statement);
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate(statement);
		
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
