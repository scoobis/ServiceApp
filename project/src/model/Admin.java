package model;

public class Admin extends Employee {

	public Admin(String phone, String email, String name, String company, int shopId, String password) {
		super(phone, email, name, company, shopId, password);
		
		this.setStatus("admin");
	}
	
	public Admin() {
		this.setStatus("admin");
	}
	
	public User createUser() {
		return null;
	}
	
	public int removeUser() {
		return -1;
	}
	
	public User editUser() {
		return null;
	}
	
	public Service createService() {
		return null;
	}
	
	public int removeService() {
		return -1;
	}
	
	public Service editService() {
		return null;
	}
	
}
