package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Order;
import model.database.OrderDatabase;

public class OrderView {
	
	private ArrayList<Cell> list = new ArrayList<Cell>();
	private ObservableList<Cell> obsList;
	private ListView<Cell> lv;
	private OrderDatabase db = new OrderDatabase();
	private ArrayList<Order> dbList;
	
	//TODO take from controller not db
	public BorderPane getCenter() {
		//TODO How do we handle what shopid to check?
		dbList = db.getAllOrders(1);
		if(list.isEmpty()) {
			for(Order o : dbList) {
				list.add(new Cell(o.getCustomerId(), o.getPrice(), o.getCompleted(), o.getServiceId(), o.getDate(), o.getShopId(), o.getCompanyId(), o.getId()));
			}
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
		TextField idField = new TextField();
		
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
		pane.add(new Label("Id:"), 0, 14);
		pane.add(idField, 0, 15);
		pane.add(button, 0, 16);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			Cell cell = new Cell(Integer.parseInt(customerIdField.getText()), Integer.parseInt(priceField.getText()), completedBox.isSelected(), 
					Integer.parseInt(serviceIdField.getText()), dateField.getText(), Integer.parseInt(shopIdField.getText()), 
					Integer.parseInt(companyIdField.getText()), Integer.parseInt(idField.getText()));
			obsList.add(cell);
			window.close();
			db.saveOrder(cell.getAsOrder());
		});
		window.setTitle("Create new order");
		window.setScene(scene);
		window.show();
	}
	
	private void edit(Cell cell) {
		GridPane pane = new GridPane();
		Button button = new Button("Edit");
		TextField customerIdField = new TextField("" + cell.getCustomerId());
		TextField priceField = new TextField("" + cell.getPrice());
		CheckBox completedBox = new CheckBox();
		completedBox.setSelected(cell.isCompleted());
		TextField serviceIdField = new TextField("" + cell.getServiceId());
		TextField dateField = new TextField("" + cell.getDate());
		TextField shopIdField = new TextField("" + cell.getShopId());
		TextField companyIdField = new TextField("" + cell.getCompanyId());
		TextField idField = new TextField("" + cell.getID());
		
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
		pane.add(new Label("Id:"), 0, 14);
		pane.add(idField, 0, 15);
		pane.add(button, 0, 16);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			Cell newCell = new Cell(Integer.parseInt(customerIdField.getText()), Float.parseFloat(priceField.getText()), completedBox.isSelected(), 
					Integer.parseInt(serviceIdField.getText()), dateField.getText(), Integer.parseInt(shopIdField.getText()), 
					Integer.parseInt(companyIdField.getText()), Integer.parseInt(idField.getText()));
			obsList.set(obsList.indexOf(cell), newCell);
			lv.refresh();
			window.close();
			Order o = db.getOrderById(Integer.parseInt(idField.getText()));
			o.setCompanyId(Integer.parseInt(companyIdField.getText()));
			o.setCompleted(completedBox.isSelected());
			o.setCustomerId(Integer.parseInt(customerIdField.getText()));
			o.setDate(dateField.getText());
			o.setPrice(Float.parseFloat(priceField.getText()));
			o.setServiceId(Integer.parseInt(serviceIdField.getText()));
			o.setShopId(Integer.parseInt(shopIdField.getText()));
			//TODO Does not work, how is editorder supposed to work
			db.editOrder(o);
		});
		window.setTitle("Edit " + cell.getCustomerId() + "'s order");
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO Does not work.
		db.deleteOrder(cell.getID());
		obsList.remove(cell);
	}
	
	public class Cell extends HBox {
		Label customerLabel = new Label();
		Label priceLabel = new Label();
		Label completedLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		int customerId;
		float price;
		boolean completed;
		int serviceId;
		String date;
		int shopId;
		int companyId;
		int id;

		Cell(int customerId, float price, boolean completed, int serviceId, String date, int shopId, int companyId, int id) {
			super();
			
			this.serviceId = serviceId;
			this.date = date;
			this.shopId = shopId;
			this.companyId = companyId;
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

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
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

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}
		
		public int getID() {
			return id;
		}
		
		public void setID(int id) {
			this.id = id;
		}
		
		public Order getAsOrder() {
			return new Order(customerId, serviceId, date, shopId, companyId, price, completed, id);
		}
	}
}
