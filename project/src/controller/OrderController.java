package controller;

import java.util.ArrayList;

import model.Customer;
import model.Order;
import model.User;
import model.database.CustomerDatabase;
import model.database.OrderDatabase;

public class OrderController {

	OrderDatabase orderDatabase;

	public OrderController() {
		orderDatabase = new OrderDatabase();
	}

	public String newOrder(int customerId, int serviceId, String date, int shopId, String company, double price) {
		if (customerId <= 0)
			return "ops, something went wrong!";
		else if (serviceId <= 0)
			return "ops, something went wrong!";
		else if (date.equalsIgnoreCase(""))
			return "ops, something went wrong!";
		else if (shopId <= 0)
			return "ops, something went wrong!";
		else if (date.equalsIgnoreCase(""))
			return "ops, something went wrong!";
		else if (company.equalsIgnoreCase(""))
			return "ops, something went wrong!";

		// TODO solve price is not set
		// TODO we need to get it from service, what's the best approach?

		User user = new User();

		boolean isSaved = orderDatabase
				.saveOrder(user.createOrder(customerId, serviceId, date, shopId, company, price));

		if (!isSaved)
			return "ops, something went wrong!";
		return "Order saved successfully!";
	}

	public String deleteOrder(int id) {
		boolean isDeleted = orderDatabase.deleteOrder(id);

		if (isDeleted)
			return id + " Deleted!";
		return "ops, something went wrong!";
	}

	public String editOrder(int id, int customerId, int serviceId, double price) {
		if (customerId <= 0)
			return "ops, something went wrong!";
		else if (serviceId <= 0)
			return "ops, something went wrong!";
		else if (id <= 0)
			return "ops, something went wrong!";

		User user = new User();

		boolean isDeleted = orderDatabase.editOrder(user.editOrder(id, customerId, serviceId, price));

		if (isDeleted)
			return "Order edited successfully!";
		return "ops, something went wrong!";
	}

    public ArrayList<Order> getAllOrders(int shopId) {
        return orderDatabase.getAllOrders(shopId);
    }
    
    public ArrayList<Order> getAllOrdersCompany(String companyName) {
    	return orderDatabase.getAllOrdersCompany(companyName);
    }

	public String setOrderToCompleted(int id) {
		if (id <= 0)
			return "ops, something went wrong!";

		boolean isSetToCompleted = orderDatabase.setOrderToCompleted(id);

		if (isSetToCompleted)
			return "Order set to completed!";
		return "ops, something went wrong!";
	}

	public String setOrderToUnCompleted(int id) {
		if (id <= 0)
			return "ops, something went wrong!";

		boolean isSetToCompleted = orderDatabase.setOrderToUnCompleted(id);

		if (isSetToCompleted)
			return "Order set to completed!";
		return "ops, something went wrong!";
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