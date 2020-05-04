package model;

public class User extends Employee {

	public User(String phone, String email, String name, String company, int shopId, String status) {
		super(phone, email, name, company, shopId, status);
	}
	
	public User() {}

	private String shopId;
	
}
