package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Admin;
import model.Employee;
import model.SuperAdmin;
import model.User;

/**
 * A class that handles the Employee Database.
 */

public class EmployeeDatabase implements DatabaseConnector {
	
	private Connection connection = DatabaseConnector.getConnection();

	/**
	 * Retrieves all Employees from a certain company.
	 * @param companyName
	 * @return ArrayList<Employee>
	 */
	
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
	
	/**
	 * Gets a User by ID.
	 * @param id
	 * @return User
	 */
	
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
			user.setId(result.getInt("id"));
			user.setName(result.getString("name"));
		}
		return user;
		
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	/**
	 * Gets a Admin by ID.
	 * @param id
	 * @return Admin
	 */
	
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
			admin.setId(result.getInt("id"));
			admin.setName(result.getString("name"));
		}
		return admin;
		
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	/**
	 * Gets a SuperAdmin by ID.
	 * @param id
	 * @return Employee
	 */
	
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
			superAdmin.setId(result.getInt("id"));
			superAdmin.setName(result.getString("name"));
		}
		return superAdmin;
		
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	/**
	 * Saves a Employee.
	 * @param e
	 * @return boolean
	 */
	
	public boolean saveEmployee(Employee e) {
			if (e instanceof Admin || e instanceof User) { return saveUserOrAdmin(e); }
			else { return saveSuperAdmin(e);  }
	}
	
	/**
	 * Helper function to the SaveEmployee function.
	 * @param e
	 * @return boolean
	 */
	
	private boolean saveUserOrAdmin(Employee e) {
		try {
		PreparedStatement create;
		create = connection.prepareStatement("INSERT INTO "+ e.getStatus() +" (company_name, shop_id, phone, email, status, name, password)"
				+ "VALUES ('"+ e.getCompanyName() +"', "
				+ e.getShopId()+","
				+ "'"+ e.getPhone()+"',"
				+ "'"+ e.getEmail() +"', "
				+ "'"+ e.getStatus() +"', "
				+ "'"+ e.getName() +"', "
				+ "'"+ e.getPassword() +"')");
			create.executeUpdate();
			return true;
			} catch (SQLException e1) {
				e1.printStackTrace(); 
				return false;
				}
	}
	
	/**
	 * Saves a SuperAdmin.
	 * @param e
	 * @return boolean
	 */
	
	private boolean saveSuperAdmin(Employee e) {
		try {
		PreparedStatement create;
		create = connection.prepareStatement("INSERT INTO super_admin (company_name, phone, email, status, name, password)"
				+ "VALUES ('"+ e.getCompanyName() +"', "
				+ "'"+ e.getPhone() +"',"
				+ "'"+ e.getEmail() +"', "
				+ "'super_admin', "
				+ "'"+ e.getName() +"', "
				+ "'"+ e.getPassword() +"')");
			create.executeUpdate();
			
			System.out.println(e.getName());
			return true;
			} catch (SQLException e1) { 
				e1.printStackTrace();
				return false;
				}
	}
	
	/**
	 * Deletes a Employee.
	 * @param e
	 * @return boolean
	 */
	
	public boolean deleteEmployee(Employee e) {
		// Not used anymore!
		try {
			PreparedStatement create;
			create = connection.prepareStatement("DELETE FROM "+ e.getStatus() +" WHERE id = " + e.getId() + ";");
			create.executeUpdate();
			return true;
			} catch (SQLException e1) { 
				e1.printStackTrace();
				return false;
				}
	}
	
	public boolean deleteUser(int id) {
		try {
			PreparedStatement create;
			create = connection.prepareStatement("DELETE FROM user WHERE id = " + id + ";");
			create.executeUpdate();
			return true;
			} catch (SQLException e1) { 
				e1.printStackTrace();
				return false;
				}
	}
	
	public boolean deleteAdmin(int id) {
		try {
			PreparedStatement create;
			create = connection.prepareStatement("DELETE FROM admin WHERE id = " + id + ";");
			create.executeUpdate();
			return true;
			} catch (SQLException e1) { 
				e1.printStackTrace();
				return false;
				}
	}
	/**
	 * Edits a Employee.
	 * @param e
	 * @return boolean
	 */
	
	public boolean editEmployee(Employee e) {
			if (e instanceof SuperAdmin) { return editSuperAdmin(e); } 
			else { return editAdminOrUser(e); }
	}
	
	/**
	 * Helper function to the editEmployee() function.
	 * @param e
	 * @return boolean
	 */
	
	private boolean editSuperAdmin(Employee e) {
		PreparedStatement edit;
		try {
		edit = connection.prepareStatement("UPDATE super_admin"
				+ " SET phone = '"+ e.getPhone() +"', "
				+ "email = '"+ e.getEmail() +"', "
				+ "name = '"+ e.getName() +"' "
				+ "WHERE id = "+ e.getId() +";");
		edit.executeUpdate();
		return true;
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return false;
			}
	}
	
	/**
	 * Helper function to the editEmployee() function.
	 * @param e
	 * @return boolean
	 */
	
	private boolean editAdminOrUser(Employee e) {
		PreparedStatement edit;
		try {
		edit = connection.prepareStatement("UPDATE " + e.getStatus()
				+ " SET shop_id = "+ e.getShopId() +","
				+ "phone = '"+ e.getPhone() +"', "
				+ "name = '"+ e.getName() +"', "
				+ "email = '"+ e.getEmail() +"' "
				+ "WHERE id = "+ e.getId() +";");
		edit.executeUpdate();
		return true;
		} catch (SQLException e1) { 
			e1.printStackTrace();
			return false;
			}
	}
	
	/**
	 * Validates a Employee.
	 * @param email
	 * @param password
	 * @return boolean
	 */
	
	public Employee validateEmployee(String email, String password) {
		try {
		String statement = "SELECT * FROM user WHERE email = '" + email + "' "
				+ "UNION "
				+ "SELECT * FROM admin WHERE email = '" + email + "';";
		
		Statement query = connection.prepareStatement(statement);
		ResultSet result = query.executeQuery(statement);
		
		Employee employee = null;
		
		while(result.next()) {
			if (result.getString("status").equalsIgnoreCase("user")) employee = new User();
			else if (result.getString("status").equalsIgnoreCase("admin")) employee = new Admin();
			else if (result.getString("status").equalsIgnoreCase("super_admin")) employee = new SuperAdmin();
			employee.setCompanyName(result.getString("company_name"));
			employee.setShopId(result.getInt("shop_id"));
			employee.setPhone(result.getString("phone"));
			employee.setEmail(result.getString("email"));
			employee.setStatus(result.getString("status"));
			employee.setId(result.getInt("id"));
			employee.setName(result.getString("name"));
			employee.setPassword(result.getString("password"));
				
			if (password.equalsIgnoreCase(result.getString("password"))) return employee;	
		}
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
		}
		return null;
	}
	
	public SuperAdmin validateSuperAdmin(String email, String password) {
		try {
			String statement = "SELECT * FROM super_admin WHERE email = '" + email + "';";
			
			Statement query = connection.prepareStatement(statement);
			ResultSet result = query.executeQuery(statement);
			
			while(result.next()) {
				SuperAdmin superAdmin = new SuperAdmin(result.getString("phone"), result.getString("email"),
						result.getString("name"), result.getString("company_name"), result.getString("password"));
				superAdmin.setId(result.getInt("id"));
				superAdmin.setStatus(result.getString("status"));
				
				if (password.equalsIgnoreCase(result.getString("password"))) return superAdmin;	
			}
			
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
		}
		return null;
	}
	
	// TEST METHODS
	
	public void resetAdmin() throws SQLException {
		System.out.println("HELLO");
		String statement = "TRUNCATE TABLE admin";
		System.out.println(statement);
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate();

	}
	
	public void resetSuperAdmin() throws SQLException {
		String statement = "TRUNCATE TABLE super_admin";
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate();

	}
	
	public void resetUser() throws SQLException {
		String statement = "TRUNCATE TABLE user";
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate();

	}

}
