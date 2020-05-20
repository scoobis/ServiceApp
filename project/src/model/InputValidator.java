package model;

public class InputValidator {
	
	public String validateServiceInput(String companyName, String title, String description, double price) {
		
		if (companyName.equalsIgnoreCase("")) return "Ops, something went wrong!";
		else if (title.equalsIgnoreCase("")) return "Title is missing!";
    	else if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
    	else if (description.equalsIgnoreCase("")) return "Description is missing!";
		
		return "";
	}
	
	public String validateCustomerInput(String name, String email, String phone, String address, int id) {
		
		if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (address.equalsIgnoreCase("")) return "Address is missing!";
        else if (id == 0) return "Ops, something went wrong!";
		
		return "";
	}
	
	public String validateEmployeeInput(String name, String email, String phone, String password, String companyName, int shopId, int id) {
		
		if (name.equalsIgnoreCase("")) return "Name is missing!";
        else if (email.equalsIgnoreCase("")) return "Email is missing!";
        else if (phone.equalsIgnoreCase("")) return "Phone is missing!";
        else if (shopId == 0 || companyName.equalsIgnoreCase("")) return "Ops, something went wrong!";
        else if (password.equalsIgnoreCase("")) return "Password is missing!";
        else if (id == 0) return "Ops, something went wrong!";
		
		return "";
	}
}
