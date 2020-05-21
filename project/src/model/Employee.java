package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Employee implements Serializable {

	private String phone;
	private String email;
	private String name;
	private String company;
	private int shopId;
	private int id;
	private String password;
	
	private String status;
	
	
	public Employee(String phone, String email, String name, String company, int shopId, String password) {
		this.status = "";
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.company = company;
		this.shopId = shopId;
		this.password = password;
	}
	
	public static Employee getLoggedInUser() throws IOException, ClassNotFoundException {
		File f = new File("LoggedInUser.txt");
		f.setWritable(true, true);
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Employee e = (Employee) ois.readObject();
		f.setWritable(false);
		f.setReadable(true);
		ois.close();
		return e;
		
	}
	
	/**
	 * Saves the Logged in Employee Object to a text file located in model.
	 * Each time we log in a Employee the .txt file will be overwritten which means there can only be one logged in Employee at the time.
	 * When we check log in credentials and get the Employee back, we can simply use Employee.logInUser(TheUserWeGotBack).
	 * And everytime we need to check for status we simply use Employee.getLoggedInUser();
	 * 
	 * + The solotuion doesnt require any further dependencies because its located as a static funtion in Employee.
	 * - We need to make sure the text isn't vulnerable for modification by any user. If so a User could rewrite her/himself to be a Admin.
	 * @param e
	 * @throws IOException
	 */
	public static void logInUser(Employee e) throws IOException {
		File f = new File("LoggedInUser.txt");
		f.setWritable(true, true);
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(e);
		oos.close();
		f.setWritable(false);
		f.setReadable(true);
	}
	
	public Employee() { this.status = ""; }
	
	public Order createOrder(int customerId, int serviceId, String date, int shopId, String company, int price) {
		Order order = new Order();
        order.setCustomerId(customerId);
        order.setServiceId(serviceId);
        order.setDate(date);
        order.setShopId(shopId);
        order.setcompanyName(company);
        order.setPrice(price);
        order.setCompleted(false);
        
        return order;
	}
	
	public int removeOrder(String id) {
		return -1;
	}
	
	public Order editOrder(int id, int customerId, int serviceId, int price) {
		Order order = new Order();
        order.setId(id);
        order.setCustomerId(customerId);
        order.setServiceId(serviceId);
        order.setPrice(price);
        
        return order;
	}
	
	public Customer createCustomer(String name, String email, String phone, String address, String company) {
		Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCompany(company);
        
        customer.setActive(true); // always true?
        
        return customer;
	}
	
	public int removeCustomer(String id) { return -1; }
	
	public Customer editCustomer(String name, String email, String phone, String address, boolean isActive, int id) { 
	Customer customer = new Customer();
    customer.setName(name);
    customer.setEmail(email);
    customer.setPhone(phone);
    customer.setAddress(address);
    customer.setId(id); 
    
    return customer;
	}
	
	public int completeOrder(String orderId) { return -1; }
	
	public int uncompleteOrder(String orderId) { return -1; }
	
	public String getCompanyName() { return company; }
	
	public String getName() { return name; }
	
	public String getEmail() {return email; }
	
	public String getPhone() { return phone; }
	
	public int getShopId() { return shopId; }
	
	public String getStatus() { return status; }
	
	public int getId() { return id; }
	
	public String getPassword() { return password; }
	
	public void setName(String name) { this.name = name; }
	
	public void setEmail(String email) { this.email = email; }
	
	public void setPhone(String phone) { this.phone = phone; }
	
	public void setCompanyName(String company) { this.company = company;}
	
	public void setStatus(String status) { this.status = status;}
	
	public void setShopId(int shopId) { this.shopId = shopId; }
	
	public void setId(int id) { this.id = id; }
	
	public void setPassword(String password) { this.password = password; }
	
}
