package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.paypal.api.payments.Invoice;
import com.paypal.base.rest.PayPalRESTException;

import model.Customer;
import model.Email;
import model.Order;
import model.PaymentInvoice;
import model.User;
import model.database.CustomerDatabase;
import model.database.OrderDatabase;

public class OrderController {

	OrderDatabase orderDatabase;
	PaymentInvoice invoice;

	public OrderController() {
		orderDatabase = new OrderDatabase();
		invoice = new PaymentInvoice();
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

		if (isSetToCompleted) {
			return "Order set to completed!";
		}
		return "ops, something went wrong!";
	}

	public String setOrderToUnCompleted(int id) {
		if (id <= 0)
			return "ops, something went wrong!";

		boolean isSetToCompleted = orderDatabase.setOrderToUnCompleted(id);

		if (isSetToCompleted) {
			return "Order set to completed!";
		}
		return "ops, something went wrong!";
	}

	public void sendInvoice(int id) {
		invoice.create(id);
		invoice.send();
	}

	public void cancelInvoice(int id) {
		try {
			invoice.cancel(invoice.retrieveInvoice(orderDatabase.getOrderById(id).getPaypalID()));
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendOrderCompleteMail(int orderID) {

		Email email = new Email();
		CustomerDatabase CD = new CustomerDatabase();
		Customer customer = CD.getCustomerById(orderDatabase.getOrderById(orderID).getCustomerId());
		email.createLink(orderDatabase.getOrderById(orderID).getPaypalID());
		email.sendMail(customer.getEmail(), orderID);
		email.start();

	}

	public boolean isEmailValid(int id) {

		Email email = new Email();
		CustomerDatabase customerDatabase = new CustomerDatabase();
		Order order = orderDatabase.getOrderById(id);
		Customer customer = customerDatabase.getCustomerById(order.getCustomerId());
		if (email.validateEmail(customer.getEmail())) {
			return true;
		} else {
			return false;
		}

	}

}