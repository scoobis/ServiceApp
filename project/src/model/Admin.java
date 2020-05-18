package model;

public class Admin extends Employee {

	public Admin(String phone, String email, String name, String company, int shopId, String password) {
		super(phone, email, name, company, shopId, password);
		
		this.setStatus("admin");
	}
	
	public Admin() {
		this.setStatus("admin");
	}
	
	public User createUser(String name, String email, String phone, String password, String companyName, int shopId) {
		User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setCompanyName(companyName);
        user.setShopId(shopId);
        
        return user;
	}
	
	public int removeUser() {
		return -1;
	}
	
	public User editUser(String phone, String email, String name, int shopId, int id) {
		User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        user.setName(name);
        user.setShopId(shopId);
        user.setId(id);
        
        return user;
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
