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

public class OrderView {
	
	private ArrayList<Cell> list = new ArrayList<Cell>();
	private ObservableList<Cell> obsList;
	private ListView<Cell> lv;
	
	public BorderPane getCenter() {
		//TODO Put info from database into "list" here instead of random loop
		for(int i = 0; i < 100; i++) {
			list.add(new Cell(Integer.toString(i), ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), ThreadLocalRandom.current().nextBoolean()
					, "service123", "date123", "shopid123", "companyid123"));
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
			Cell cell = new Cell(customerIdField.getText(), Integer.parseInt(priceField.getText()), completedBox.isSelected(), serviceIdField.getText(), 
					dateField.getText(), shopIdField.getText(), companyIdField.getText());
			obsList.add(cell);
			window.close();
			//TODO Call controller here
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
			Cell newCell = new Cell(customerIdField.getText(), Integer.parseInt(priceField.getText()), completedBox.isSelected(), "service123", 
									"date123", "shopid123", "companyid123");
			obsList.set(obsList.indexOf(cell), newCell);
			lv.refresh();
			window.close();
			//TODO Call controller here
		});
		window.setTitle("Edit " + cell.getCustomerId() + "'s order");
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO Call controller here
		obsList.remove(cell);
	}
	
	public class Cell extends HBox {
		Label customerLabel = new Label();
		Label priceLabel = new Label();
		Label completedLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		String customerId;
		int price;
		boolean completed;
		String serviceId;
		String date;
		String shopId;
		String companyId;

		Cell(String customerId, int price, boolean completed, String serviceId, String date, String shopId, String companyId) {
			super();
			
			this.serviceId = serviceId;
			this.date = date;
			this.shopId = shopId;
			this.companyId = companyId;
			
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

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public boolean isCompleted() {
			return completed;
		}

		public void setCompleted(boolean completed) {
			this.completed = completed;
		}

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		
		
	}
}
