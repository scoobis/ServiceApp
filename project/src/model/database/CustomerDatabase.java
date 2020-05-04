package model.database;

import security.PasswordHasher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import model.Customer;

public class CustomerDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {

	Connection connection = DatabaseConnector.getConnection();
	
	public ArrayList<Customer> getAllCustomers() {
		return null;
	}
	
	public Customer getCustomerById(String id) {
		return null;
	}
	
	public void saveCustomer(Customer customer) throws NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			
			PreparedStatement create;
			create = connection.prepareStatement("INSERT INTO customer (phone, email, name, address, active)"
						+ "VALUES ('"+ customer.getPhone() +"', "
						+ customer.getEmail()+","
						+ customer.getName()+","
						+ "'"+ customer.getAddress() +"', "
						+ "'"+ customer.isActive() +"', "
						+ "'"+ customer.getPassword() +"')"); // TODO Add password??
			create.executeUpdate();
			
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public int deleteCustomer(String id) {
		return -1;
	}
	
	public Customer editCustomer(String id, Customer c) {
		return null;
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
