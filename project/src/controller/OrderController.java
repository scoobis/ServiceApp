package controller;

import java.util.ArrayList;

import model.Order;
import model.User;
import model.database.OrderDatabase;

/**
 *  Handles calls from View to Model which concerns any calls to the Order class.
 *
 */
public class OrderController {

    OrderDatabase orderDatabase;

    /**
     * Constructor
     */
    
    public OrderController() {
        orderDatabase = new OrderDatabase();
    }

    /**
     * Creates a new Order.
     * @param customerId
     * @param serviceId
     * @param date
     * @param shopId
     * @param company
     * @param price
     * @return String
     */
    
    public String newOrder(int customerId, int serviceId, String date, int shopId, String company, double price) {
    	
    	// TODO Why isn't this in InputValidator?
        if (customerId <= 0) return "ops, something went wrong!";
        else if (serviceId <= 0) return "ops, something went wrong!";
        else if (date.equalsIgnoreCase("")) return "ops, something went wrong!";
        else if (shopId <= 0) return "ops, something went wrong!";
        else if (date.equalsIgnoreCase("")) return "ops, something went wrong!";
        else if (company.equalsIgnoreCase("")) return "ops, something went wrong!";

        // TODO solve price is not set
        // TODO we need to get it from service, what's the best approach?

        User user = new User();

        boolean isSaved = orderDatabase.saveOrder(user.createOrder(customerId, serviceId, date, shopId, company, price));

        if (!isSaved) return "ops, something went wrong!";
        return "Order saved successfully!";
    }

    /**
     * Deletes a order.
     * @param id
     * @return String
     */
    
    public String deleteOrder(int id) {
        boolean isDeleted = orderDatabase.deleteOrder(id);

        if (isDeleted) return id + " Deleted!";
        return "ops, something went wrong!";
    }
    
    /**
     * Edits a Order
     * @param id
     * @param customerId
     * @param serviceId
     * @param price
     * @return String
     */

    public String editOrder(int id, int customerId, int serviceId, double price) {
        if (customerId <= 0) return "ops, something went wrong!";
        else if (serviceId <= 0) return "ops, something went wrong!";
        else if (id <= 0) return "ops, something went wrong!";

        User user = new User();

        boolean isDeleted = orderDatabase.editOrder(user.editOrder(id, customerId, serviceId, price));

        if (isDeleted) return "Order Edited!";
        return "ops, something went wrong!";
    }
    
    /**
     * Gets all Orders from a certain shop.
     * @param shopId The shop the retrieve all orders from.
     * @return ArrayList<Order>
     */

    public ArrayList<Order> getAllOrders(int shopId) {
        return orderDatabase.getAllOrders(shopId);
    }
    
    public ArrayList<Order> getAllOrdersCompany(String companyName) {
    	return orderDatabase.getAllOrdersCompany(companyName);
    }
    
    /**
     * Sets a order to Completed status.
     * @param id The Order to change status on.
     * @return String
     */

	public String setOrderToCompleted(int id) {
		if (id <= 0)
			return "ops, something went wrong!";

		boolean isSetToCompleted = orderDatabase.setOrderToCompleted(id);

		if (isSetToCompleted)
			return "Order set to completed!";
		return "ops, something went wrong!";
	}

    /**
     * Sets a order to Uncompleted status.
     * @param id The Order to change status on.
     * @return String
     */

	public String setOrderToUnCompleted(int id) {
		if (id <= 0)
			return "ops, something went wrong!";

		boolean isSetToCompleted = orderDatabase.setOrderToUnCompleted(id);

		if (isSetToCompleted)
			return "Order set to completed!";
		return "ops, something went wrong!";
	}
	
	public boolean setPaidStatus(int id, String paidStatus) {
		return orderDatabase.setPaidStatus(id, paidStatus);
	}

	public void sendOrderCompleteMail(Order order) {
/*
		Email email = new Email();
		CustomerDatabase CD = new CustomerDatabase();
		Customer customer = CD.getCustomerById(order.getCustomerId());
		email.sendMail(customer.getEmail(), order.getId());
*/
	}

}