package model;

public class Order {

	private String customerId;
	private String serviceId;
	private String date;
	private String shopId;
	private String companyId;
	private int price;
	private boolean completed;
	
	public boolean sendOrderComplete() {
		return false;
	}
	
	public boolean completed() {
		return false;
	}
	
}
