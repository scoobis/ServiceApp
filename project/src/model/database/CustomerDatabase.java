package model.database;

import java.util.ArrayList;
import java.util.Observer;

import model.Customer;

public class CustomerDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {

	public ArrayList<Customer> getAllCustomers() {
		return null;
	}
	
	public Customer getCustomerById(String id) {
		return null;
	}
	
	public void saveCustomer(Customer c) {
		
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
