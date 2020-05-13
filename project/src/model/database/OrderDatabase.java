package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observer;
import model.Order;

public class OrderDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {
	
	private Connection connection = DatabaseConnector.getConnection();

	public ArrayList<Order> getAllOrders(int shopId) {
		ArrayList<Order> orders = new ArrayList<>();
		
		try {
			String statement = "SELECT * FROM orders WHERE shop_id = " + shopId + ";";
			Statement query = connection.prepareStatement(statement);
			ResultSet result = query.executeQuery(statement);
			
			while(result.next()) {
				orders.add(getOrderById(result.getInt("id")));
			}
			
			return orders;
			
		} catch (SQLException e1) { 
			e1.printStackTrace(); 
			return null;
		}
	}
	
	public Order getOrderById(int id) {
		Order order = new Order();
		try {
			String statement = "SELECT * FROM orders WHERE id = " + id + ";";
			Statement query = connection.prepareStatement(statement);
			ResultSet result = query.executeQuery(statement);
			
			while(result.next()) {
				order.setServiceId(result.getInt("service_id"));
				order.setCustomerId(result.getInt("customer_id"));
				order.setShopId(result.getInt("shop_id"));
				order.setCompanyId(result.getString("company_name"));
				order.setPrice(result.getInt("price"));
				order.setCompleted(result.getBoolean("completed"));
				order.setDate(result.getString("date"));
			}
			return order;
			
		} catch (SQLException e1) { e1.printStackTrace(); }
		return null;
	}
	
	public void saveOrder(Order o) {
		PreparedStatement create;
		try {
			create = connection.prepareStatement("INSERT INTO orders (service_id, customer_id, shop_id, company_name, price, completed, date)"
						+ "VALUES ("+ o.getServiceId() +", "
						+ o.getCustomerId() +","
						+ o.getShopId() +","
						+ "'"+ o.getCompanyId() +"', "
						+ o.getPrice() +","
						+ o.getCompleted() +","
						+ "'"+ o.getDate() +"');");
			create.executeUpdate();
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public void deleteOrder(int id) {
		PreparedStatement create;
		try {
			create = connection.prepareStatement("DELETE FROM orders WHERE id = " + id + ";");
			create.executeUpdate();
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public void editOrder(int id, Order o) {
		PreparedStatement edit;
		try {
			edit = connection.prepareStatement("UPDATE orders "
						+ "SET service_id = "+ o.getServiceId() +", "
						+ "customer_id = "+ o.getCustomerId() +","
						+ "shop_id = " + o.getShopId() +","
						+ "company_name = '"+ o.getCompanyId() +"', "
						+ "price = "+ o.getPrice() +","
						+ "completed = "+ o.getCompleted() +","
						+ "date = '"+ o.getDate() +"' "
						+ "WHERE id = "+ id +";");
			edit.executeUpdate();
		} catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public void reset() throws SQLException {
		String statement = "TRUNCATE TABLE orders";
		PreparedStatement query = connection.prepareStatement(statement);
		query.executeUpdate();

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
