package model;

public class User extends Employee {

	public User(String phone, String email, String name, String company, int shopId, int id) {
		super(phone, email, name, company, shopId);
		
		this.setStatus("user");
		
	}
	
	public User() {
		this.setStatus("user");
	}
	
}
