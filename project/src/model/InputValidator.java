package model;

public class InputValidator {
	
	public String validateService(String companyName, String title, String description, int price) {
		if (companyName.equalsIgnoreCase("")) return "ops, something went wrong!";
		else if (title.equalsIgnoreCase("")) return "Title is missing!";
    	else if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
    	else if (description.equalsIgnoreCase("")) return "Description is missing!";
		return "";
	}
}
