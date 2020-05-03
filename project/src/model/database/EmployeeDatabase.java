package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;

import model.Employee;

public class EmployeeDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {
	
	private Connection connection = null;
	
	public EmployeeDatabase() {
		connection = DatabaseConnector.getConnection();
	}

	public ArrayList<Employee> getAllEmployees() {
		return null;
	}
	
	public Employee getEmployeeById(String id) {
		return null;
	}
	
	public void saveEmployee(Employee e) {
		PreparedStatement create;
		try {
			create = connection.prepareStatement("INSERT INTO user (company_name, shop_id, phone, email, password)"
						+ "VALUES ('"+ e.getCompanyName() +"', "
						+ e.getShopId()+","
						+ e.getPhone()+","
						+ "'"+ e.getEmail() +"', "
						+ "'password')"); // TODO Add password??
			create.executeUpdate();
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public int deleteEmployee(String id) {
		return -1;
	}
	
	public Employee editEmployee(String id, Employee e) {
		return null;
	}
	
	public boolean validateEmployee(String email, String password) {
		return false;
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
