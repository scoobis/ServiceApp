package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observer;

import model.Admin;
import model.Employee;
import model.SuperAdmin;
import model.User;

public class EmployeeDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {
	
	private Connection connection = DatabaseConnector.getConnection();

	public ArrayList<Employee> getAllEmployees(String companyName) {
		ArrayList<Employee> employees = new ArrayList<>();
		
		try {
		String statement = "SELECT id, status FROM user WHERE user.company_name = '" + companyName + "' "
				+ "UNION "
				+ "SELECT id, status FROM admin WHERE company_name = '" + companyName + "' "
				+ "UNION "
				+ "SELECT id, status FROM super_admin WHERE company_name = '" + companyName + "';";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			if (result.getString("status").equalsIgnoreCase("user")) {
				employees.add(getUserById(result.getInt("id")));
			} else if (result.getString("status").equalsIgnoreCase("admin")) {
				employees.add(getAdminById(result.getInt("id")));
			} else if (result.getString("status").equalsIgnoreCase("super_admin")) {
				employees.add(getSuperAdminById(result.getInt("id")));
			}
		}
		
		return employees;
		
		} catch (SQLException e1) { 
			e1.printStackTrace();
			return null;
		}
	}
	
	public User getUserById(int id) {
		User user = new User();
		try {
		String statement = "SELECT * FROM user WHERE id = "+ id +";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			user.setCompanyName(result.getString("company_name"));
			user.setShopId(result.getInt("shop_id"));
			user.setPhone(result.getString("phone"));
			user.setEmail(result.getString("email"));
			user.setStatus(result.getString("status"));
		}
		return user;
		
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	public Admin getAdminById(int id) {
		Admin admin = new Admin();
		try {
		String statement = "SELECT * FROM admin WHERE id = "+ id +";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			admin.setCompanyName(result.getString("company_name"));
			admin.setShopId(result.getInt("shop_id"));
			admin.setPhone(result.getString("phone"));
			admin.setEmail(result.getString("email"));
			admin.setStatus(result.getString("status"));
		}
		return admin;
		
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	public Employee getSuperAdminById(int id) {
		Employee superAdmin = new SuperAdmin();
		try {
		String statement = "SELECT * FROM super_admin WHERE id = "+ id +";";
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			superAdmin.setCompanyName(result.getString("company_name"));
			superAdmin.setPhone(result.getString("phone"));
			superAdmin.setEmail(result.getString("email"));
			superAdmin.setStatus(result.getString("status"));
		}
		return superAdmin;
		
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	public void saveEmployee(Employee e) {
			if (e.getStatus().equalsIgnoreCase("Admin") || e.getStatus().equalsIgnoreCase("User")) { saveUserOrAdmin(e); }
			else { saveSuperAdmin(e);  }
	}
	
	private void saveUserOrAdmin(Employee e) {
		try {
		PreparedStatement create;
		create = connection.prepareStatement("INSERT INTO "+ e.getStatus().toLowerCase() +" (company_name, shop_id, phone, email, status, password)"
				+ "VALUES ('"+ e.getCompanyName() +"', "
				+ e.getShopId()+","
				+ "'"+ e.getPhone()+"',"
				+ "'"+ e.getEmail() +"', "
				+ "'"+ e.getStatus().toLowerCase() +"', "
				+ "'password')"); // TODO Add password??
			create.executeUpdate();
			} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	private void saveSuperAdmin(Employee e) {
		try {
		PreparedStatement create;
		create = connection.prepareStatement("INSERT INTO super_admin (company_name, phone, email, status, password)"
				+ "VALUES ('"+ e.getCompanyName() +"', "
				+ "'"+ e.getPhone() +"',"
				+ "'"+ e.getEmail() +"', "
				+ "'super_admin', "
				+ "'password')"); // TODO Add password??
			create.executeUpdate();
			} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public void deleteEmployee(int id, String status) {
		//test
		try {
			PreparedStatement create;
			create = connection.prepareStatement("DELETE FROM "+ status.toLowerCase() +" WHERE id = " + id + ";");
			create.executeUpdate();
			} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public void editEmployee(int id, Employee e) {
			if (e.getStatus().equalsIgnoreCase("super_admin")) { editSuperAdmin(id, e); } 
			else { editAdminOrUser(id, e); }
	}
	
	private void editSuperAdmin(int id, Employee e) {
		PreparedStatement edit;
		try {
		edit = connection.prepareStatement("UPDATE " + e.getStatus().toLowerCase()
				+ " SET phone = '"+ e.getPhone() +"', "
				+ "email = '"+ e.getEmail() +"' "
				+ "WHERE id = "+ id +";");
		edit.executeUpdate();
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	private void editAdminOrUser(int id, Employee e) {
		PreparedStatement edit;
		try {
		edit = connection.prepareStatement("UPDATE " + e.getStatus().toLowerCase()
				+ " SET shop_id = "+ e.getShopId() +","
				+ "phone = '"+ e.getPhone() +"', "
				+ "email = '"+ e.getEmail() +"' "
				+ "WHERE id = "+ id +";");
		edit.executeUpdate();
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public boolean validateEmployee(String email, String password) {
		System.out.println(email);
		try {
		String statement = "SELECT password FROM user WHERE email = '" + email + "' "
				+ "UNION "
				+ "SELECT password FROM admin WHERE email = '" + email + "' "
				+ "UNION "
				+ "SELECT password from super_admin = " + email + ";";
		
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		while(result.next()) {
			return password.equalsIgnoreCase(result.getString("password"));	
		}
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
		}
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
