package view;

import java.util.ArrayList;

import controller.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Customer;

public class CustomerView {
	
	private ArrayList<Cell> list;
	private ListView<Cell> lv;
	
	private CustomerController customerController;
	
	public CustomerView() {
		list = new ArrayList<Cell>();
		customerController = new CustomerController();
	}
	
	public BorderPane getCenter() {
		ObservableList<Cell> obsList;
		
		setList();
		
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
	
	private void setList() {
		
		ArrayList<Customer> allCustomers = customerController.getAllCustomers("company"); // TODO get company from logged in user
		
		list.clear();
		
		for (Customer customer : allCustomers) {
			list.add(new Cell(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getAddress()));
		}
		
	}
	
	private void create() {
		GridPane pane = new GridPane();
		Button button = new Button("Create");
		TextField idField = new TextField();
		TextField nameField = new TextField();
		TextField emailField = new TextField();
		TextField phoneField = new TextField();
		TextField addressField = new TextField();
		
		// TODO remove id
		pane.add(new Label("Id:"), 0, 0);
		pane.add(idField, 0, 1);
		pane.add(new Label("Name:"), 0, 2);
		pane.add(nameField, 0, 3);
		pane.add(new Label("Email:"), 0, 4);
		pane.add(emailField, 0, 5);
		pane.add(new Label("Phone:"), 0, 6);
		pane.add(phoneField, 0, 7);
		pane.add(new Label("Address:"), 0, 8);
		pane.add(addressField, 0, 9);
		pane.add(button, 0, 10);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			String email = emailField.getText();
			String phone = phoneField.getText();
			String name = nameField.getText();
			String address = addressField.getText();
			String companyName = "company"; // TODO get company from logged in user;
			
			// TODO display message
			String message = customerController.createCustomer(name, email, phone, address, companyName);
			
			lv.refresh();
			window.close();
			//TODO Call controller here
			
			// update view
			setList();
		});
		window.setTitle("Create new customer");
		window.setScene(scene);
		window.show();
	}
	
	private void edit(Cell cell) {
		GridPane pane = new GridPane();
		Button button = new Button("Edit");
		TextField idField = new TextField("" + cell.getID());
		TextField nameField = new TextField("" + cell.getName());
		TextField emailField = new TextField("" + cell.getEmail());
		TextField phoneField = new TextField("" + cell.getPhone());
		TextField addressField = new TextField("" + cell.getAddress());
		
		// TODO remove id
		// TODO add isActive check
		pane.add(new Label("Id:"), 0, 0);
		pane.add(idField, 0, 1);
		pane.add(new Label("Name:"), 0, 2);
		pane.add(nameField, 0, 3);
		pane.add(new Label("Email:"), 0, 4);
		pane.add(emailField, 0, 5);
		pane.add(new Label("Phone:"), 0, 6);
		pane.add(phoneField, 0, 7);
		pane.add(new Label("Address:"), 0, 8);
		pane.add(addressField, 0, 9);
		pane.add(button, 0, 10);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			int id = cell.getID();
			String email = emailField.getText();
			String phone = phoneField.getText();
			String name = nameField.getText();
			String address = addressField.getText();
			
			// TODO set isActive
			// TODO display message
			String message = customerController.editCustomer(name, email, phone, address, true, id);
			
			lv.refresh();
			window.close();
			
			// update view
			setList();
		});
		window.setTitle("Edit " + cell.getID());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO display message
		String message = customerController.deleteCustomer(cell.getID(), cell.getName());
				
		lv.refresh();
				
		// update view
		setList();
	}
	
	public class Cell extends HBox {
		Label idLabel = new Label();
		Label nameLabel = new Label();
		Label emailLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		int id;
		String name;
		String email;
		String phone;
		String address;

		Cell(int id, String name, String email, String phone, String address) {
			super();
			
			this.phone = phone;
			this.address = address;
			
			this.id = id;
			idLabel.setText("Id: " + id);
			idLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(idLabel, Priority.ALWAYS);
			
			this.name = name;
			nameLabel.setText("Company: " + name);
			nameLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(nameLabel, Priority.ALWAYS);
			
			this.email = email;
			emailLabel.setText("Email: " + email);
			emailLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(emailLabel, Priority.ALWAYS);
			
			editButton.setOnAction(e -> {
				edit(this);
			});
			
			removeButton.setOnAction(e -> {
				remove(this);
			});
			
			this.getChildren().addAll(idLabel, nameLabel, emailLabel, editButton, removeButton);
		}

		public int getID() {
			return id;
		}

		public void setID(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
	}
}
