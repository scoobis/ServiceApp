package view;

import java.util.ArrayList;

import controller.ShopController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Shop;

public class ShopView {

	private ArrayList<Cell> list;
	private ListView<Cell> lv;
	
	private ShopController shopController;
	
	public ShopView() {
		list = new ArrayList<Cell>();
		shopController = new ShopController();
	}
	
	public BorderPane getCenter() {
		ObservableList<Cell> obsList;
		
		setList();
		
		BorderPane bp = new BorderPane();
		Button createButton = new Button("Create");
		
		//TODO Make this check permissions
		createButton.setOnAction(e -> create());
		
		lv = new ListView<Cell>();
		obsList = FXCollections.observableList(list);
		lv.setItems(obsList);
		bp.setCenter(lv);
		bp.setTop(createButton);
		return bp;
	}
	
	private void setList() {
		ArrayList<Shop> allShops = shopController.getAllShops("company"); // TODO add company name fom loggedin user
		
		list.clear();
		
		for (Shop shop : allShops) {
			list.add(new Cell(shop.getId(), shop.getCompanyName(), shop.getAddress(), shop.getName()));
		}
	}
	
	private void create() {
		GridPane pane = new GridPane();
		Button button = new Button("Create");
		
		TextField nameField = new TextField();
		TextField addressField = new TextField();
		
		pane.add(new Label("Name:"), 0, 0);
		pane.add(nameField, 0, 1);
		pane.add(new Label("Address:"), 0, 2);
		pane.add(addressField, 0, 3);
		pane.add(button, 0, 4);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			String companyName = "company"; // TODO get company name from logged in user
			String name = nameField.getText();
			String address = addressField.getText();
			
			lv.refresh();
			window.close();
			
			String message = shopController.newShop(name, address, companyName);
			
			// update view
			setList();
			
			// must display message last
			if (message.contains("successfully"))
				displaySuccessMessage(message);
			else
				displayErrorMessage(message);
		});
		
		window.setTitle("Create new employee");
		window.setScene(scene);
		window.show();
	}
	
	private void displaySuccessMessage(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	private void displayErrorMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	private void edit(Cell cell) {
		GridPane pane = new GridPane();
		Button button = new Button("Edit");
		
		TextField nameField = new TextField("" + cell.getName());
		TextField addressField = new TextField("" + cell.getAddress());
		
		pane.add(new Label("Name:"), 0, 0);
		pane.add(nameField, 0, 1);
		pane.add(new Label("Address:"), 0, 2);
		pane.add(addressField, 0, 3);
		pane.add(button, 0, 4);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			String name = nameField.getText();
			String address = addressField.getText();
			
			lv.refresh();
			window.close();
			
			String message = shopController.editShop(cell.getID(), name, address);
			
			// update view
			setList();
			
			// must display message last
			if (message.contains("successfully"))
				displaySuccessMessage(message);
			else
				displayErrorMessage(message);
		});
		
		window.setTitle("Create new employee");
		window.setScene(scene);
		window.show();
		
	}
	
	private void remove(Cell cell) {
		String message = shopController.deleteShop(cell.getID(), cell.getName());
		
		lv.refresh();
		
		// update view
		setList();
		
		// must display message last
			displayErrorMessage(message);
	}
	
	private class Cell extends HBox {
		
		private int id;
		private String companyName, address, name;
		
		private Label nameLabel = new Label();
		private Label addressLabel = new Label();
		
		private Button editButton = new Button("Edit");
		private Button removeButton = new Button("Remove");
		
		Cell(int id, String companyName, String address, String name) {
			super();
			
			this.id = id;
			this.companyName = companyName;
			this.address = address;
			this.name = name;
			
			nameLabel.setText("Name: " + name);
			nameLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(nameLabel, Priority.ALWAYS);
			
			addressLabel.setText("Address: " + address);
			addressLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(addressLabel, Priority.ALWAYS);
			
			editButton.setOnAction(e -> {
				edit(this);
			});
			
			removeButton.setOnAction(e -> {
				remove(this);
			});
			
			this.getChildren().addAll(nameLabel, addressLabel, editButton, removeButton);
			
		}
		
		public void setId(int id) { this.id = id; }
		
		public void setCompanyName(String companyName) { this.companyName = companyName; }
		
		public void setName(String name) { this.name = name; }
		
		public void setAddress(String address) { this.address = address; }
		
		public int getID() { return id; }
		
		public String getCompanyName() { return companyName; }
		
		public String getAddress() { return address; }
		
		public String getName() { return name; }
		
	}
	
}
