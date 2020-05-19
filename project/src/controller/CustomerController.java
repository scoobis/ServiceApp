package controller;

import java.sql.SQLException;
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

    public String deleteCustomer(Customer customer) {
        boolean isDeleted = customerDatabase.deleteCustomer(customer.getId());

        if (isDeleted) return customer.getName() + " Deleted!";
        return "ops, something went wrong!";
    }

    public String editCustomer(String name, String email, String phone, String address, boolean isActive, int id) {
    	String inputCheck = inputValidator.validateCustomerInput(name, email, phone, address, id);

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
    	
        User user = new User();

        boolean isDeleted = customerDatabase.editCustomer(user.editCustomer(name, email, phone, address, isActive, id));

        if (isDeleted) return name + " Edited!";
        return "ops, something went wrong!";

    }

    public ArrayList<Customer> getAllCustomers(String company) throws SQLException {

        ArrayList<Customer> customers = customerDatabase.getAllCustomers(company);

        return customers;
    }
}
