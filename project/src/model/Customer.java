package model;

/**
 * Representation of the "Customer"-role of the system.
 */
public class Customer {

	private String email;
	private int id; // ID SHOULD BE HERE
	private String phone;
	private String name;
	private String address;
	// private String password; // Added password to customer, when registering a customer use the PasswordHashed to immidietaly hash the password.
	private boolean active;
	private String company;
	
	/**
	 * Empty Constructor.
	 */
	
	public Customer() {
		
	}
	
	/**
	 * Constructor.
	 * @param email
	 * @param phone
	 * @param name
	 * @param address
	 * @param active
	 */
	
	public Customer(String email, String phone, String name, String address, boolean active) { // ID SHOULD NOT BE HERE
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.active = active;
	}
	
	// Getters and Setters for all the fields.
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// ID SHOULD HAVE A GETTER METHOD
	public int getId() {
		return id;
	}
	
	// ID SHOULD HAVE A SETTER METHOD
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
