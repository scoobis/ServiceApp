package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.OrderController;
import controller.ServiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Order;
import model.Service;

public class OrderView {
	
	private ArrayList<Cell> uncompList;
	private ArrayList<Cell> compList;
	private ListView<Cell> uncompLv;
	private ListView<Cell> compLv;
	
	private OrderController orderController;
	
	public OrderView() {
		orderController = new OrderController();
		uncompList = new ArrayList<Cell>();
		compList = new ArrayList<Cell>();
	}
	
	//TODO Try with TableView
	public BorderPane getCenter(Stage window) {
		ObservableList<Cell> uncompObsList;
		ObservableList<Cell> compObsList;
		
		setList();
			
		BorderPane bp = new BorderPane();
		Button createButton = new Button();
		
		createButton.setGraphic(new ImageView(new Image("view/images/create.png")));
		createButton.setOnAction(e -> create());
		createButton.setTooltip(new Tooltip("Create new order"));
		
		uncompLv = new ListView<Cell>();
		compLv = new ListView<Cell>();
		uncompObsList = FXCollections.observableList(uncompList);
		compObsList = FXCollections.observableList(compList);
		uncompLv.setItems(uncompObsList);
		compLv.setItems(compObsList);
		
		uncompLv.prefWidthProperty().bind(window.widthProperty().multiply(.495));
		compLv.prefWidthProperty().bind(window.widthProperty().multiply(.495));
		bp.getStylesheets().add("view/css/list-buttons.css");
		bp.getStylesheets().add("view/css/tooltips.css");
		
		bp.setTop(createButton);
		bp.setLeft(uncompLv);
		bp.setRight(compLv);
		
		return bp;
	}
	
	private void setList() {
		
		ArrayList<Order> allOrders = orderController.getAllOrders(1); // TODO get shopId from logged in user
		
		uncompList.clear();
		compList.clear();
		
		for(Order o : allOrders) {
			if(!o.getCompleted())
				uncompList.add(new Cell(o.getCustomerId(), o.getPrice(), o.getCompleted(), o.getServiceId(), o.getDate(), o.getShopId(), o.getcompanyName(), o.getId()));
			else
				compList.add(new Cell(o.getCustomerId(), o.getPrice(), o.getCompleted(), o.getServiceId(), o.getDate(), o.getShopId(), o.getcompanyName(), o.getId()));
		}
	}
	
	private void create() {
		GridPane pane = new GridPane();
		Button button = new Button("Create");
		IntTextField customerIdField = new IntTextField();
		DoubleTextField priceField = new DoubleTextField();
		ComboBox<String> serviceBox = new ComboBox<String>();
		
		for(Service service : FXCollections.observableList(new ServiceController().getAllServices("company"))) { //TODO Get company from logged in user
			serviceBox.getItems().add("" + service.getId() + ": " + service.getTitle());
		}
		
		pane.add(new Label("Customer Id:"), 0, 0);
		pane.add(customerIdField, 0, 1);
		pane.add(new Label("Price:"), 0, 2);
		pane.add(priceField, 0, 3);
		pane.add(new Label("Service:"), 0, 4);
		pane.add(serviceBox, 0, 5);
		pane.add(button, 0, 6);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			int serviceId = Integer.parseInt(serviceBox.getValue().substring(0, serviceBox.getValue().indexOf(':')));
			int customerId = Integer.parseInt(customerIdField.getText());
			String date = this.getTodaysDate();
			int shopId = 1; // TODO get shopId from logged in user
			String companyName = "company"; // TODO get company from logged in user
			double price = Double.parseDouble(priceField.getText());
			
			uncompLv.refresh();
			compLv.refresh();
			window.close();
			
			String message = orderController.newOrder(customerId, serviceId, date, shopId, companyName, price);
			Popup.display(message);
			
			setList();
		});
		
		window.setTitle("Create new order");
		window.setScene(scene);
		window.show();
	}
	
	private void edit(Cell cell) {
		
		GridPane pane = new GridPane();
		Button editBtn = new Button("Edit");
		IntTextField customerIdField = new IntTextField("" + cell.getCustomerId());
		DoubleTextField priceField = new DoubleTextField("" + cell.getPrice());
		IntTextField shopIdField = new IntTextField("" + cell.getShopId());
		
		pane.add(new Label("Customer Id:"), 0, 0);
		pane.add(customerIdField, 0, 1);
		pane.add(new Label("Price:"), 0, 2);
		pane.add(priceField, 0, 3);
		pane.add(new Label("Shop Id:"), 0, 4);
		pane.add(shopIdField, 0, 5);
		pane.add(editBtn, 0, 6);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		editBtn.setOnAction(e -> {
			
			int id = cell.getID();
			int customerId = Integer.parseInt(customerIdField.getText());
			int serviceId = cell.getServiceId();
			double price = Double.parseDouble(priceField.getText());
			
			uncompLv.refresh();
			compLv.refresh();
			window.close();
			
			String message = orderController.editOrder(id, customerId, serviceId, price);
			Popup.display(message);
			
			setList();
		});
		
		window.setTitle("Edit " + cell.getCustomerId() + "'s order");
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		String message = orderController.deleteOrder(cell.getID());
		Popup.display(message);
		
		uncompLv.refresh();
		compLv.refresh();
		
		setList();
	}
	
	private String getTodaysDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
	}
	
	public class Cell extends HBox {
		Label customerLabel = new Label();
		Label priceLabel = new Label();
		Label dateLabel = new Label();
		Button editButton = new Button();
		Button removeButton = new Button();
		Button completeButton = new Button();
		int customerId;
		double price;
		boolean completed;
		int serviceId;
		String date;
		int shopId;
		String companyName;
		int id;

		//TODO Add unpaid
		Cell(int customerId, double price, boolean completed, int serviceId, String date, int shopId, String companyName, int id) {
			super();
			
			this.serviceId = serviceId;
			this.completed = completed;
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
			
			this.date = date;
			dateLabel.setText("Date: " + date);
			dateLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(dateLabel, Priority.ALWAYS);
			
			if(completed) {
				completeButton.setGraphic(new ImageView(new Image("view/images/complete.png")));
				completeButton.setTooltip(new Tooltip("Uncomplete " + id));
				completeButton.setOnAction(e -> {
					orderController.setOrderToUnCompleted(id);
					uncompLv.refresh();
					compLv.refresh();
					
					setList();
				});
			} else {
				completeButton.setGraphic(new ImageView(new Image("view/images/uncomplete.png")));
				completeButton.setTooltip(new Tooltip("Complete " + id));
				completeButton.setOnAction(e -> {
					orderController.setOrderToCompleted(id);
					uncompLv.refresh();
					compLv.refresh();
					
					setList();
				});
			}
			
			editButton.setGraphic(new ImageView(new Image("view/images/edit.png")));
			editButton.setTooltip(new Tooltip("Edit " + id));
			editButton.setOnAction(e -> {
				edit(this);
			});
			
			removeButton.setGraphic(new ImageView(new Image("view/images/remove.png")));
			removeButton.setTooltip(new Tooltip("Remove " + id));
			removeButton.setOnAction(e -> {
				remove(this);
			});
			
			this.getChildren().addAll(customerLabel, priceLabel, dateLabel, completeButton ,editButton, removeButton);
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
