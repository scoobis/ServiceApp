package model;

public class Order {

	private int customerId;
	private int serviceId;
	private String date;
	private int shopId;
	private int companyId; // Changed from String to int
	private float price; // Changed to float
	private boolean completed;
	private int id;
	
	// Company ID String?
	public Order(int customerId, int serviceId, String date, int shopId, String companyId, int price, boolean completed, int id) {
		this.customerId = customerId;
		this.serviceId = serviceId;
		this.date = date;
		this.shopId = shopId;
		this.companyId = companyId;
		this.price = price;
		this.completed = completed;
		this.id = id;
	}
	
	public Order() {}
	
	public boolean sendOrderComplete() {
		return false;
	}
	
	public boolean completed() {
		return false;
	}
	
	public void setCustomerId(int customerId) { this.customerId = customerId; }
	public void setServiceId(int serviceId) { this.serviceId = serviceId; }
	public void setDate(String date) { this.date = date; }
	public void setShopId(int shopId) { this.shopId = shopId; }
	public void setCompanyId(int companyId) { this.companyId = companyId; }
	public void setPrice(float price) { this.price = price; }
	public void setCompleted(boolean completed) { this.completed = completed; }
	public void setId(int id) { this.id = id; }

	
	public int getCustomerId() { return customerId; }
	public int getServiceId() { return serviceId; }
	public String getDate() { return date; }
	public int getShopId() { return shopId; }
	public int getCompanyId() { return companyId; }
	public float getPrice() { return price; }
	public boolean getCompleted() { return completed; }
	public int getId( ) { return id; }
	
}
