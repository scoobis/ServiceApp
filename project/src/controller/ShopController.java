package controller;

import java.util.ArrayList;

import model.InputValidator;
import model.Shop;
import model.SuperAdmin;
import model.database.ShopDatabase;

public class ShopController {
	
	ShopDatabase shopDatabase;
	InputValidator inputValidator;
	
	public ShopController() {
		shopDatabase = new ShopDatabase();
		inputValidator = new InputValidator();
	}
	
	public ArrayList<Shop> getAllShops(String companyName) {
		return shopDatabase.getAllShops(companyName);
	}
	
	public String newShop(String name, String address, String companyName) {
		String inputCheck = inputValidator.validateShopInput(name, address, companyName);
		
		if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
		
		SuperAdmin superAdmin = new SuperAdmin();
		
		boolean isSaved = shopDatabase.saveShop(superAdmin.createShop(name, address, companyName));
		
		if (isSaved) return "Shop created successfully";
		return "Ops, something went wrong!";	
	}
	
	public String editShop(int id, String name, String address) {
		String inputCheck = inputValidator.validateShopInput(name, address, "co"); // co as stub
		if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
		if (id == 0) return "Ops, something went wrong!";
		
		
		SuperAdmin superAdmin = new SuperAdmin();
		
		boolean isSaved = shopDatabase.editShop(superAdmin.editShop(id, name, address));
		
		if (isSaved) return "Shop edited successfully";
		return "Ops, something went wrong!";	
	}
	
	public String deleteShop(int id, String name) {
		
		boolean isDeleted = shopDatabase.deleteShop(id);
		
		if (isDeleted) return name + " Deleted!";
		return "Ops, something went wrong!";
	}
}
