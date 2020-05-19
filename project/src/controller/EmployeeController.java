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

	private EmployeeDatabase employeeDatabase;
	private PasswordHasher hash;
	
	public EmployeeController() {
		employeeDatabase = new EmployeeDatabase();
		hash = new PasswordHasher();
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
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0 || companyName.equalsIgnoreCase("")) return "ops, something went wrong!";
        else  if (password.equalsIgnoreCase("")) return "Password is missing!";

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setCompanyName(companyName);
        user.setShopId(shopId);

        boolean isSaved = employeeDatabase.saveEmployee(user);

        if (!isSaved) return "ops, something went wrong!";
        return "User created successfully";
    }
	
	public String deleteUser(Employee e) {
        boolean isDeleted = employeeDatabase.deleteEmployee(e);

        if (isDeleted) return e.getName() + " Deleted!";
        return "ops, something went wrong!";
    }
	
	public String editUser(String phone, String email, String name, int shopId, int id) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0) return "ops, something went wrong!";

        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        user.setName(name);
        user.setShopId(shopId);
        user.setId(id);

        boolean isEdited = employeeDatabase.editEmployee(user);
        if (isEdited) return "User edited successfully";
        return "Ops, something went wrong!";
    }
	
	public String newAdmin(String name, String email, String phone, String password, String companyName, int shopId) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0 || companyName.equalsIgnoreCase("")) return "ops, something went wrong!";
        else if (password.equalsIgnoreCase("")) return "Password is missing!";

        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setPassword(password);
        admin.setCompanyName(companyName);
        admin.setShopId(shopId);

        boolean isSaved = employeeDatabase.saveEmployee(admin);

        if (!isSaved) return "ops, something went wrong!";
        return "Admin created successfully";
    }
	
	public String deleteAdmin(Employee e) {
        boolean isDeleted = employeeDatabase.deleteEmployee(e);

        if (isDeleted) return e.getName() + " Deleted!";
        return "ops, something went wrong!";
    }
	
	public String editAdmin(String phone, String email, String name, int shopId, int id) {
        if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0) return "ops, something went wrong!";

        Admin admin = new Admin();
        admin.setPhone(phone);
        admin.setEmail(email);
        admin.setName(name);
        admin.setShopId(shopId);
        admin.setId(id);

        boolean isEdited = employeeDatabase.editEmployee(admin);
        if (isEdited) return "Admin edited successfully";
        return "Ops, something went wrong!";
    }
	
	public ArrayList<Employee> getAllEmployees(String companyName) {
        return employeeDatabase.getAllEmployees(companyName);
    }
	
	//TODO These needs implementation
	public boolean validateEmployee(Employee e, String p) {
			return false;
	}

	public boolean login(String email, String password) {
		return false;
	}	
}
