package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.InputValidator;
import model.Service;
import model.SuperAdmin;
import model.database.ServiceDatabase;

public class ServiceController {

    private ServiceDatabase serviceDatabase;
    private InputValidator inputValidator;

    public ServiceController() {
        serviceDatabase = new ServiceDatabase();
        inputValidator = new InputValidator();
    }

    public String newService(String companyName, String title, String description, double price) {
    	String inputCheck = inputValidator.validateServiceInput(companyName, title, description, price);

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;
    	
        SuperAdmin superAdmin = new SuperAdmin();

        boolean isSaved = serviceDatabase.saveService(superAdmin.createService(companyName, title, description, price));

        if (!isSaved) return "ops, something went wrong!";
        return "Service saved successfully";
    }

    public ArrayList<Service> getAllServices(String companyName) throws SQLException {
        return serviceDatabase.getAllServices(companyName);
    }

    public String editService(String companyName, String title, String description, double price, int id) {
    	if (id == 0) return "Ops, something went wrong!";
    	
    	String inputCheck = inputValidator.validateServiceInput(companyName, title, description, price);

    	if (!inputCheck.equalsIgnoreCase("")) return inputCheck;

        SuperAdmin superAdmin = new SuperAdmin();
        
        boolean isEdited = serviceDatabase.editService(superAdmin.editService(companyName, title, description, price, id));
        if (isEdited) return "Service edited successfully";
        return "Ops, something went wrong!";
    }

    public String deleteService(Service service) {
        boolean isDeleted = serviceDatabase.deleteService(service.getId());

        if (isDeleted) return service.getTitle() + " Deleted!";
        return "ops, something went wrong!";
    }
}
