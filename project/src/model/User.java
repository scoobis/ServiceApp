package model;

public class User extends Employee {

	public User(String phone, String email, String name, String company, int shopId, String password) {
		super(phone, email, name, company, shopId, password);
		
		this.setStatus("user");
		
	}
	
	public User() {
		this.setStatus("user");
	}
	
}
