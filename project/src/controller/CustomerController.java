package controller;

import java.util.ArrayList;

import model.Customer;
import model.InputValidator;
import model.User;
import model.database.CustomerDatabase;

public class CustomerController {

    private CustomerDatabase customerDatabase;
    private InputValidator inputValidator;

    public CustomerController() {
        customerDatabase = new CustomerDatabase();
        inputValidator = new InputValidator();
    }

    public String createCustomer(String name, String email, String phone, String address, String company) {
    	String inputCheck = inputValidator.validateCustomerInput(name, email, phone, address, 1); // id as stub

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;

        User user = new User();

        boolean customerIsSaved = customerDatabase.saveCustomer(user.createCustomer(name, email, phone, address, company));

        if (!customerIsSaved) return "ops, something went wrong!";

        return "Customer successfully added!";
    }

    public String deleteCustomer(int id, String name) {
        boolean isDeleted = customerDatabase.deleteCustomer(id);

        if (isDeleted) return name + " Deleted!";
        return "ops, something went wrong!";
    }

    public String editCustomer(String name, String email, String phone, String address, boolean isActive, int id) {
    	String inputCheck = inputValidator.validateCustomerInput(name, email, phone, address, id);

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
    	
        User user = new User();

        boolean isDeleted = customerDatabase.editCustomer(user.editCustomer(name, email, phone, address, isActive, id));

        if (isDeleted) return name + " Edited successfully!";
        return "ops, something went wrong!";

    }

    public ArrayList<Customer> getAllCustomers(String company) {

        ArrayList<Customer> customers = customerDatabase.getAllCustomers(company);

        return customers;
    }
}
