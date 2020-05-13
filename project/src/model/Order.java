package model;

public class Order {

	private int customerId;
	private int serviceId;
	private String date;
	private int shopId;
	private String companyId;
	private int price;
	private boolean completed;
	
	// Company ID String?
	public Order(int customerId, int serviceId, String date, int shopId, String companyId, int price, boolean completed) {
		this.customerId = customerId;
		this.serviceId = serviceId;
		this.date = date;
		this.shopId = shopId;
		this.companyId = companyId;
		this.price = price;
		this.completed = completed;
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
	public void setCompanyId(String companyId) { this.companyId = companyId; }
	public void setPrice(int price) { this.price = price; }
	public void setCompleted(boolean completed) { this.completed = completed; }
	
	public int getCustomerId() { return customerId; }
	public int getServiceId() { return serviceId; }
	public String getDate() { return date; }
	public int getShopId() { return shopId; }
	public String getCompanyId() { return companyId; }
	public int getPrice() { return price; }
	public boolean getCompleted() { return completed; }
	
}
