package model;

import model.database.DatabaseConnector;

public abstract class Employee {

	private String phone;
	private String email;
	private String name;
	private String company;
	private String shopId;
	private DatabaseConnector db;
	
	public Order createOrder() {
		return null;
	}
	
	public int removeOrder(String id) {
		return -1;
	}
	
	public Order editOrder(String id) {
		return null;
	}
	
	public Customer createCustomer() {
		return null;
	}
	
	public int removeCustomer(String id) {
		return -1;
	}
	
	public Customer editCustomer(String id) {
		return null;
	}
	
	public int completeOrder(String orderId) {
		return -1;
	}
	
	public int uncompleteOrder(String orderId) {
		return -1;
	}
	
	public String getCompanyName() {
		return company;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getShopId() {
		return shopId;
	}
	
}
