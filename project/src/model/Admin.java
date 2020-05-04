package model;

public class Admin extends Employee {
	
	private String shopId;

	public Admin(String phone, String email, String name, String company, int shopId, String status) {
		super(phone, email, name, company, shopId, status);

	}
	
	public Admin() {}
	
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
