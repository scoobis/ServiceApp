package controller;

import java.util.ArrayList;

import model.Shop;
import model.database.ShopDatabase;

public class ShopController {
	
	ShopDatabase shopDatabase;
	
	public ShopController() {
		shopDatabase = new ShopDatabase();
	}
	
	public ArrayList<Shop> getAllShops(String companyName) {
		return shopDatabase.getAllShops(companyName);
	}
	
	public String newShop(String name, String address, String companyName) {
		// TODO move to validate input
		if (name.equalsIgnoreCase("")) return "Name is missing!";
		else if (address.equalsIgnoreCase("")) return "Address is missing!";
		else if (companyName.equalsIgnoreCase("")) return "Company is missing!";
		
		// TODO move to super Admin
		Shop shop = new Shop();
		shop.setName(name);
		shop.setAddress(address);
		shop.setCompanyName(companyName);
		
		boolean isSaved = shopDatabase.saveShop(shop);
		
		if (isSaved) return "Shop created successfully";
		return "Ops, something went wrong!";	
	}
	
	public String editShop(int id, String name, String address) {
		// TODO move to validate input
		if (name.equalsIgnoreCase("")) return "Name is missing!";
		else if (address.equalsIgnoreCase("")) return "Address is missing!";
		else if (id == 0) return "Ops, something went wrong!";
		
		// TODO move to super Admin
		Shop shop = new Shop();
		shop.setName(name);
		shop.setAddress(address);
		shop.setId(id);
		
		boolean isSaved = shopDatabase.editShop(shop);
		
		if (isSaved) return "Shop created successfully";
		return "Ops, something went wrong!";	
	}
	
	public String deleteShop(int id, String name) {
		
		boolean isDeleted = shopDatabase.deleteShop(id);
		
		if (isDeleted) return name + " Deleted!";
		return "Ops, something went wrong!";
	}
}
