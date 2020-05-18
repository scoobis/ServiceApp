package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Service;
import model.SuperAdmin;
import model.database.ServiceDatabase;

public class ServiceController {

    private ServiceDatabase serviceDatabase;

    public ServiceController() {
        serviceDatabase = new ServiceDatabase();
    }

    public String newService(String companyName, String title, String description, int price) {

    	if (companyName.equalsIgnoreCase("")) return "ops, something went wrong!";
    	if (title.equalsIgnoreCase("")) return "Title is missing!";
    	else if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
    	else if (description.equalsIgnoreCase("")) return "Description is missing!";

        SuperAdmin superAdmin = new SuperAdmin();

        boolean isSaved = serviceDatabase.saveService(superAdmin.createService(companyName, title, description, price));

        if (!isSaved) return "ops, something went wrong!";
        return "Service saved successfully";
    }

    public ArrayList<Service> getAllServices(String companyName) throws SQLException {
        return serviceDatabase.getAllServices(companyName);
    }

    public String editService(String companyName, String title, String description, int price, int id) {
        if (title.equalsIgnoreCase("")) return "Title is missing!";
        if (price == -1 ) return "Price is missing!"; // make sure to pass in -1 if price is missing
        if (description.equalsIgnoreCase("")) return "Description is missing!";
        if (companyName.equalsIgnoreCase("")) return "Ops, something went wrong!";

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
