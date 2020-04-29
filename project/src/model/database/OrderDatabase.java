package model.database;

import java.util.ArrayList;
import java.util.Observer;

import model.Order;

public class OrderDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {

	public ArrayList<Order> getAllOrders() {
		return null;
	}
	
	public Order getOrderById(String id) {
		return null;
	}
	
	public void saveOrder(Order o) {
		
	}
	
	public int deleteOrder(String id) {
		return -1;
	}
	
	public Order editOrder(String id, Order o) {
		return null;
	}
	
	@Override
	public void attach(Observer o) {
		
	}

	@Override
	public void detach(Observer o) {
		
	}

	@Override
	public void update() {
		
	}

}
