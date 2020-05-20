package model;

public class Customer {

	private String email;
	private int id;
	private String phone;
	private String name;
	private String address;
	// private String password; // Added password to customer, when registering a customer use the PasswordHashed to immidietaly hash the password.
	private boolean active;
	private String company;
	
	public Customer() {
		
	}
	
	public Customer(String email, String phone, String name, String address, boolean active, int id) {
		this.email = email;
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.active = active;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCompany() { return company; }
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	/*public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/
	
	public void setCompany(String comapny) {
		this.company = comapny;
	}
	
}
