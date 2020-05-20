package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.OrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Order;

public class OrderView {
	
	private ArrayList<Cell> list = new ArrayList<Cell>();
	private ObservableList<Cell> obsList;
	private ListView<Cell> lv;
	private OrderController orderController = new OrderController();
	private ArrayList<Order> allOrders;
	
	public BorderPane getCenter() {
		allOrders = orderController.getAllOrders(1);
		
		// instead of checking
		list.clear();
		
			for(Order o : allOrders) {
				list.add(new Cell(o.getCustomerId(), o.getPrice(), o.getCompleted(), o.getServiceId(), o.getDate(), o.getShopId(), o.getcompanyName(), o.getId()));
			}
			
		BorderPane bp = new BorderPane();
		Button createButton = new Button("Create");
		
		createButton.setOnAction(e -> create());
		
		lv = new ListView<Cell>();
		obsList = FXCollections.observableList(list);
		lv.setItems(obsList);
		bp.setCenter(lv);
		bp.setTop(createButton);
		return bp;
	}
	
	private void create() {
		GridPane pane = new GridPane();
		Button button = new Button("Create");
		TextField customerIdField = new TextField();
		TextField priceField = new TextField();
		CheckBox completedBox = new CheckBox();
		TextField serviceIdField = new TextField();
		TextField dateField = new TextField();
		TextField shopIdField = new TextField();
		TextField companyIdField = new TextField();
		
		pane.add(new Label("Customer Id:"), 0, 0);
		pane.add(customerIdField, 0, 1);
		pane.add(new Label("Price:"), 0, 2);
		pane.add(priceField, 0, 3);
		pane.add(new Label("Completed:"), 0, 4);
		pane.add(completedBox, 0, 5);
		pane.add(new Label("Service Id:"), 0, 6);
		pane.add(serviceIdField, 0, 7);
		pane.add(new Label("Date:"), 0, 8);
		pane.add(dateField, 0, 9);
		pane.add(new Label("Shop Id:"), 0, 10);
		pane.add(shopIdField, 0, 11);
		pane.add(new Label("Company Id:"), 0, 12);
		pane.add(companyIdField, 0, 13);
		pane.add(button, 0, 14);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			// TODO remove datefield, companyName, shopField
			
			int customerId = Integer.parseInt(customerIdField.getText());
			int serviceId = Integer.parseInt(serviceIdField.getText());
			String date = this.getTodaysDate();
			int shopId = 1; // TODO get shopId from logged in user
			String companyName = "company"; // TODO get company from logged in user
			double price = Double.parseDouble(priceField.getText());
			
			lv.refresh();
			window.close();
			
			// TODO display message
			String message = orderController.newOrder(customerId, serviceId, date, shopId, companyName, price);
			
			// update view
			getCenter();
		});
		
		window.setTitle("Create new order");
		window.setScene(scene);
		window.show();
	}
	
	private void edit(Cell cell) {
		
		GridPane pane = new GridPane();
		Button editBtn = new Button("Edit");
		TextField customerIdField = new TextField("" + cell.getCustomerId());
		TextField priceField = new TextField("" + cell.getPrice());
		CheckBox completedBox = new CheckBox();
		completedBox.setSelected(cell.isCompleted());
		TextField serviceIdField = new TextField("" + cell.getServiceId());
		TextField dateField = new TextField("" + cell.getDate());
		TextField shopIdField = new TextField("" + cell.getShopId());
		TextField companyIdField = new TextField("" + cell.getCompanyName());
		
		// TODO only include customerId, shopId, Price, completed
		pane.add(new Label("Customer Id:"), 0, 0);
		pane.add(customerIdField, 0, 1);
		pane.add(new Label("Price:"), 0, 2);
		pane.add(priceField, 0, 3);
		pane.add(new Label("Completed:"), 0, 4);
		pane.add(completedBox, 0, 5);
		pane.add(new Label("Service Id:"), 0, 6);
		pane.add(serviceIdField, 0, 7);
		pane.add(new Label("Date:"), 0, 8);
		pane.add(dateField, 0, 9);
		pane.add(new Label("Shop Id:"), 0, 10);
		pane.add(shopIdField, 0, 11);
		pane.add(new Label("Company Id:"), 0, 12);
		pane.add(companyIdField, 0, 13);
		pane.add(editBtn, 0, 14);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		editBtn.setOnAction(e -> {
			
			int id = cell.getID();
			int customerId = Integer.parseInt(customerIdField.getText());
			int serviceId = Integer.parseInt(serviceIdField.getText());
			double price = Double.parseDouble(priceField.getText());
			
			lv.refresh();
			window.close();
			
			// TODO display message
			String message = orderController.editOrder(id, customerId, serviceId, price);
			
			// update view
			getCenter();
		});
		
		window.setTitle("Edit " + cell.getCustomerId() + "'s order");
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO display message
		String message = orderController.deleteOrder(cell.getID());
		
		// update view TODO does not updated properly
		getCenter();
	}
	
	private String getTodaysDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
	}
	
	public class Cell extends HBox {
		Label customerLabel = new Label();
		Label priceLabel = new Label();
		Label completedLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		int customerId;
		double price;
		boolean completed;
		int serviceId;
		String date;
		int shopId;
		String companyName;
		int id;

		Cell(int customerId, double price, boolean completed, int serviceId, String date, int shopId, String companyName, int id) {
			super();
			
			this.serviceId = serviceId;
			this.date = date;
			this.shopId = shopId;
			this.companyName = companyName;
			this.id = id;
			
			this.customerId = customerId;
			customerLabel.setText("Customer id: " + customerId);
			customerLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(customerLabel, Priority.ALWAYS);
			
			this.price = price;
			priceLabel.setText("Price: " + price);
			priceLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(priceLabel, Priority.ALWAYS);
			
			this.completed = completed;
			completedLabel.setText("Completed: " + completed);
			completedLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(completedLabel, Priority.ALWAYS);
			
			editButton.setOnAction(e -> {
				edit(this);
			});
			
			removeButton.setOnAction(e -> {
				remove(this);
			});
			
			this.getChildren().addAll(customerLabel, priceLabel, completedLabel, editButton, removeButton);
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public boolean isCompleted() {
			return completed;
		}

		public void setCompleted(boolean completed) {
			this.completed = completed;
		}

		public int getServiceId() {
			return serviceId;
		}

		public void setServiceId(int serviceId) {
			this.serviceId = serviceId;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getShopId() {
			return shopId;
		}

		public void setShopId(int shopId) {
			this.shopId = shopId;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
		public int getID() {
			return id;
		}
		
		public Order getAsOrder() {
			return new Order(customerId, serviceId, date, shopId, companyName, price, completed);
		}
	}
}
