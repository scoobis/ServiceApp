package model;

/**
 * A representation of a Super Admin in the system.
 */
 
@SuppressWarnings("serial")
public class User extends Employee {

	/**
	 * Constructor
	 * @param phone
	 * @param email
	 * @param name
	 * @param company
	 * @param shopId
	 * @param password
	 */
	
	public User(String phone, String email, String name, String company, int shopId, String password) {
		super(phone, email, name, company, shopId, password);
		
		this.setStatus("user");
		
	}
	
	/**
	 * Empty Constructor.
	 */
	
	public User() {
		this.setStatus("user");
	}
	
}
