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
		int i = 0;
		while(result.next()) {
			customers.add(getCustomerById(result.getString(i)));
			i++;
		}
		
		return customers;
	}
	
	public Customer getCustomerById(String id) throws SQLException {
		
		String statement = "SELECT * FROM customer WHERE id=" + id + ";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		int i = 0;
		
		while(result.next()) {
			// Create a Customer here.
			System.out.println(result.getString(i));
			i++;
		}
		
		Customer fetchedCustomer = new Customer();
		
		return fetchedCustomer;
	}
	
	public void saveCustomer(Customer customer) throws InvalidKeySpecException {
		try {
			
			PreparedStatement query;
			query = connection.prepareStatement("INSERT INTO customer (phone, email, name, address, active)"
						+ "VALUES ('"+ customer.getPhone() +"', "
						+ customer.getEmail()+","
						+ customer.getName()+","
						+ "'"+ customer.getAddress() +"', "
						+ "'"+ customer.isActive() +"', "
						+ "'"+ customer.getPassword() +"')");
			query.executeUpdate();
			
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public int deleteCustomer(String id) {
		String statement = "DELETE FROM customer WHERE id=" + id + ";";
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
	
	public Customer editCustomer(String id, Customer c) throws NoSuchAlgorithmException, InvalidKeySpecException {
		deleteCustomer(id);
		saveCustomer(c);
		
		return c;
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
