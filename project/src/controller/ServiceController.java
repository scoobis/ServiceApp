package controller;

import java.util.ArrayList;

import model.InputValidator;
import model.Service;
import model.SuperAdmin;
import model.database.ServiceDatabase;

/**
 * Handles calls from View to Model which concerns any calls to the Service class.
 */

public class ServiceController {

    private ServiceDatabase serviceDatabase;
    private InputValidator inputValidator;

    /**
     * Constructor
     */
    
    public ServiceController() {
        serviceDatabase = new ServiceDatabase();
        inputValidator = new InputValidator();
    }

    /**
     * Creates a new Service
     * @param companyName
     * @param title
     * @param description
     * @param price
     * @return
     */
    
    public String newService(String companyName, String title, String description, double price) {
    	String inputCheck = inputValidator.validateServiceInput(companyName, title, description, price);

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
    	
        SuperAdmin superAdmin = new SuperAdmin();

        boolean isSaved = serviceDatabase.saveService(superAdmin.createService(companyName, title, description, price));

        if (!isSaved) return "ops, something went wrong!";
        return "Service saved successfully";
    }

    /**
     * Gets all services from a company.
     * @param companyName The name of the company
     * @return ArrayList<Service>
     */
    
    public ArrayList<Service> getAllServices(String companyName) {
        return serviceDatabase.getAllServices(companyName);
    }
    
    /**
     * Edits a Service.
     * @param companyName
     * @param title
     * @param description
     * @param price
     * @param id
     * @return String
     */

    public String editService(String companyName, String title, String description, double price, int id) {
    	if (id == 0) return "Ops, something went wrong!";
    	
    	String inputCheck = inputValidator.validateServiceInput(companyName, title, description, price);

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;

        SuperAdmin superAdmin = new SuperAdmin();
        
        boolean isEdited = serviceDatabase.editService(superAdmin.editService(companyName, title, description, price, id));
        if (isEdited) return "Service edited successfully";
        return "Ops, something went wrong!";
    }

    /**
     * Deletes a Service.
     * @param id
     * @param title
     * @return String
     */
    
    public String deleteService(int id, String title) {
        boolean isDeleted = serviceDatabase.deleteService(id);

        if (isDeleted) return title + " Deleted!";
        return "ops, something went wrong!";
    }
}
