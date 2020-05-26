package model;

/**
 * A representation of a Order in the system.
 */

public class Order {

	private int customerId;
	private int serviceId;
	private String date;
	private int shopId;
	private String companyName; // Changed from String to int
	private double price; // Changed to double
	private boolean completed;
	private int id;
	private String paypalID;
	
	private String paidStatus;

	/**
	 * Constructor.
	 * @param customerId
	 * @param serviceId
	 * @param date
	 * @param shopId
	 * @param companyName
	 * @param price
	 * @param completed
	 */
	
	public Order(int customerId, int serviceId, String date, int shopId, String companyName, double price, boolean completed) {
		this.customerId = customerId;
		this.serviceId = serviceId;
		this.date = date;
		this.shopId = shopId;
		this.companyName = companyName;
		this.price = price;
		this.completed = completed;
	}

	/**
	 * Empty Constructor.
	 */
	
	public Order() {
	}
	
	// GETTERS AND SETTERS

	public boolean sendOrderComplete() {
		return false;
	}

	public boolean completed() {
		return false;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public String getDate() {
		return date;
	}

	public int getShopId() {
		return shopId;
	}

	public String getcompanyName() {
		return companyName;
	}

	public double getPrice() {
		return price;
	}

	public boolean getCompleted() {
		return completed;
	}

	public int getId() {
		return id;
	}
	
	public String getPaidStatus() {
		return paidStatus;
	}

	public String getPaypalID() {
		return paypalID;
	}

	public void setPaypalID(String paypalID) {
		this.paypalID = paypalID;
	}

}
