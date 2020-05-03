package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateTables {
Connection connection = null;
	
	public CreateTables() {
		connection = DatabaseConnector.getConnection();
	}
	
	public void executeCreateAllTables() {
		this.createSuperAdmin();
		this.createAdmin();
		this.createUser();
		this.createShop();
		this.createCompany();
		this.createOrder();
		this.createService();
		this.createCustomer();
		System.out.println("All create functions executed!");
	}
	
	private void createSuperAdmin() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS super_admin"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "company_name varchar(25), "
					+ "phone varchar(25), "
					+ "email varchar(155), "
					+ "password varchar(512), "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createAdmin() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS admin"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "company_name varchar(25), "
					+ "shop_id int, "
					+ "phone varchar(25), "
					+ "email varchar(155), "
					+ "password varchar(512), "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createUser() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "company_name varchar(25), "
					+ "shop_id int, "
					+ "phone varchar(25), "
					+ "email varchar(155), "
					+ "password varchar(512), "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createShop() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS shop"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "company_name varchar(25), "
					+ "adress varchar(128), "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createCompany() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS company"
					+ "(name varchar(64), "
					+ "adress varchar(128), "
					+ "PRIMARY KEY(name))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createOrder() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customer_order"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "service_id int, "
					+ "customer_id int, "
					+ "shop_id int, "
					+ "company_name varchar(64), "
					+ "price int, "
					+ "completed boolean, "
					+ "date DATE, "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createService() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS service"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "company_name varchar(64), "
					+ "title varchar(64), "
					+ "description TEXT, "
					+ "price int, "
					+ "uniques boolean, "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	private void createCustomer() {
		try {
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customer"
					+ "(id int NOT NULL AUTO_INCREMENT, "
					+ "phone varchar(25), "
					+ "email varchar(155), "
					+ "name varchar(128), "
					+ "adress varchar(128), "
					+ "status varchar(64), "
					+ "company_name varchar(64), "
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
}
