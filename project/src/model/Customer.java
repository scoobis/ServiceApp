package model;

/**
 * Representation of the "Customer"-role of the system.
 */
public class Customer {

	private String email;
	private int id;
	private String phone;
	private String name;
	private String address;
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
	
	public Customer(String email, String phone, String name, String address) {
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = address;
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
	
	public void setCompany(String comapny) {
		this.company = comapny;
	}
	
}
