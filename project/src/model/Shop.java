package model;

public class Shop {
	
	private int id;
	private String companyName, address, name;
	
	public Shop(String companyName, String address, String name) {
		this.companyName = companyName;
		this.address = address;
		this.companyName = name;
	}
	
	public Shop() {}
		
	public void setId(int id) { this.id = id; }
	
	public void setCompanyName(String companyName) { this.companyName = companyName; }
	
	public void setName(String name) { this.name = name; }
	
	public void setAddress(String address) { this.address = address; }
	
	public int getId() { return id; }
	
	public String getCompanyName() { return companyName; }
	
	public String getAddress() { return address; }
	
	public String getName() { return name; }
				
}
