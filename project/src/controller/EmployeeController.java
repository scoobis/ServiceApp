package controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import model.Admin;
import model.Employee;
import model.InputValidator;
import model.SuperAdmin;
import model.database.EmployeeDatabase;
import security.PasswordHasher;

public class EmployeeController {

	private EmployeeDatabase employeeDatabase;
	private PasswordHasher hash;
	
	private InputValidator inputValidator;
	
	public EmployeeController() {
		employeeDatabase = new EmployeeDatabase();
		hash = new PasswordHasher();
		
		inputValidator = new InputValidator();
	}
	
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
		employeeDatabase.saveEmployee(superAdmin);
	}
	
	public String newUser(String name, String email, String phone, String password, String companyName, int shopId) {
		
		String inputCheck = inputValidator.validateEmployeeInput(name, email, phone, password, companyName, shopId, 1); // id as stub

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
        
        Admin admin = new Admin();

        boolean isSaved = employeeDatabase.saveEmployee(admin.createUser(name, email, phone, password, companyName, shopId));

        if (!isSaved) return "ops, something went wrong!";
        return "User created successfully";
    }
	
	public String deleteUser(Employee e) {
		
        boolean isDeleted = employeeDatabase.deleteEmployee(e);

        if (isDeleted) return e.getName() + " Deleted!";
        return "ops, something went wrong!";
    }
	
	public String editUser(String phone, String email, String name, int shopId, int id) {
		
		String inputCheck = inputValidator.validateEmployeeInput(name, email, phone, "password", "company", shopId, id); // password and company as stub

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
        
        Admin admin = new Admin();

        boolean isEdited = employeeDatabase.editEmployee(admin.editUser(phone, email, name, shopId, id));
        if (isEdited) return "User edited successfully";
        return "Ops, something went wrong!";
    }
	
	public String newAdmin(String name, String email, String phone, String password, String companyName, int shopId) {
		
		String inputCheck = inputValidator.validateEmployeeInput(name, email, phone, password, companyName, shopId, 1); // id as stub

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;

        SuperAdmin superAdmin = new SuperAdmin();

        boolean isSaved = employeeDatabase.saveEmployee(superAdmin.createAdmin(name, email, phone, password, companyName, shopId));

        if (!isSaved) return "ops, something went wrong!";
        return "Admin created successfully";
    }
	
	public String deleteAdmin(Employee e) {
		
        boolean isDeleted = employeeDatabase.deleteEmployee(e);

        if (isDeleted) return e.getName() + " Deleted!";
        return "ops, something went wrong!";
    }
	
	public String editAdmin(String phone, String email, String name, int shopId, int id) {
		
		String inputCheck = inputValidator.validateEmployeeInput(name, email, phone, "password", "company", shopId, id); // password and company as stub

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
        
        SuperAdmin superAdmin = new SuperAdmin();

        boolean isEdited = employeeDatabase.editEmployee(superAdmin.editAdmin(phone, email, name, shopId, id));
        if (isEdited) return "Admin edited successfully";
        return "Ops, something went wrong!";
    }
	
	public ArrayList<Employee> getAllEmployees(String companyName) {
        return employeeDatabase.getAllEmployees(companyName);
    }
	
	public boolean validateEmployee(Employee e, String p) {
			return false;
	}

	public boolean login(String email, String password) {
		return false;
	}	
}
