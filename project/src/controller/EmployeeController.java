package controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import model.Admin;
import model.Employee;
import model.SuperAdmin;
import model.User;
import model.database.EmployeeDatabase;
import security.PasswordHasher;
import view.IView;
import view.LoginView;

public class EmployeeController {

	private IView view;
	private Employee employee;
	private EmployeeDatabase data = new EmployeeDatabase();
	private PasswordHasher hash = new PasswordHasher();
	public void newSuperAdmin(ArrayList<String> list) {
		Employee superAdmin = new SuperAdmin();
		superAdmin.setEmail(list.get(0));
		superAdmin.setCompanyName(list.get(1));
		superAdmin.setName(list.get(2));
		superAdmin.setPhone(list.get(3));
		try {
			String hashedPassword = hash.hashPassword(list.get(4));
			//superAdmin.setPassword(hashedPassword); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		superAdmin.setStatus("Super_Admin");
		data.saveEmployee(superAdmin);
	}
	
	public void newUser(User u) {
		
	}
	
	public void deleteUser(String id) {
		
	}
	
	public User editUser(String id, User newUser) {
		return null;
	}
	
	public void newAdmin(Admin a) {
		
	}
	
	public void deleteAdmin(String id) {
		
	}
	
	public Admin editAdmin(String id, Admin newAdmin) {
		return null;
	}
	
	public void listEmployees() {
		
	}
	
	public boolean validateEmployee(Employee e, String p) {
			return false;
	}

	public boolean login(String email, String password) {
		//När password variablen är fixad
		//String hashedPassword = hash.hashPassword(password);
		/*if(data.validateEmployee(email, hashedPassword))
			return true;
		else
			return false;	 */
		
		if(data.validateEmployee(email, password))
			return true;
		else
			return false;
	}	
}
