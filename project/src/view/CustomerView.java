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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Customer;
import model.Employee;
import view.essentials.PhoneTextField;
import view.essentials.Popup;

public class CustomerView {
	
	private ArrayList<Cell> list;
	private ListView<Cell> lv;
	
	private CustomerController customerController;
	
	private Employee loggedInUser;
	
	public CustomerView() {
		list = new ArrayList<Cell>();
		customerController = new CustomerController();
	}
	
	public BorderPane getCenter() {
		ObservableList<Cell> obsList;
		
			loggedInUser = Employee.getLoggedInUser();
		
		setList();
		
		BorderPane bp = new BorderPane();
		Button createButton = new Button();
		createButton.setGraphic(new ImageView(new Image("view/images/create.png")));
		createButton.setTooltip(new Tooltip("Create new customer"));
		
		createButton.setOnAction(e -> create());
		
		bp.getStylesheets().add("view/css/list-buttons.css");
		lv = new ListView<Cell>();
		obsList = FXCollections.observableList(list);
		lv.setItems(obsList);
		bp.setCenter(lv);
		bp.setTop(createButton);
		return bp;
	}
	
	private void setList() {
		
		ArrayList<Customer> allCustomers = customerController.getAllCustomers(loggedInUser.getCompanyName());
		
		list.clear();
		
		if (allCustomers.isEmpty()) list.add(new Cell());
		
		for (Customer customer : allCustomers) {
			list.add(new Cell(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getAddress()));
		}
		
	}
	
	private void create() {
		GridPane pane = new GridPane();
		Button button = new Button("Create");
		TextField nameField = new TextField();
		TextField emailField = new TextField();
		PhoneTextField phoneField = new PhoneTextField();
		TextField addressField = new TextField();
		
		pane.add(new Label("Name:"), 0, 0);
		pane.add(nameField, 0, 1);
		pane.add(new Label("Email:"), 0, 2);
		pane.add(emailField, 0, 3);
		pane.add(new Label("Phone:"), 0, 4);
		pane.add(phoneField, 0, 5);
		pane.add(new Label("Address:"), 0, 6);
		pane.add(addressField, 0, 7);
		pane.add(button, 0, 8);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			String email = emailField.getText();
			String phone = phoneField.getText();
			String name = nameField.getText();
			String address = addressField.getText();
			String companyName = loggedInUser.getCompanyName();
			
			String message = customerController.createCustomer(name, email, phone, address, companyName);
			
			lv.refresh();
			window.close();
			
			setList();
			
			if (message.contains("successfully"))
				Popup.displaySuccessMessage(message);
			else
				Popup.displayErrorMessage(message);
		});
		window.setTitle("Create new customer");
		window.setScene(scene);
		window.show();
	}
	
	private void edit(Cell cell) {
		GridPane pane = new GridPane();
		Button button = new Button("Edit");
		TextField nameField = new TextField("" + cell.getName());
		TextField emailField = new TextField("" + cell.getEmail());
		PhoneTextField phoneField = new PhoneTextField("" + cell.getPhone());
		TextField addressField = new TextField("" + cell.getAddress());
		
		pane.add(new Label("Name:"), 0, 0);
		pane.add(nameField, 0, 1);
		pane.add(new Label("Email:"), 0, 2);
		pane.add(emailField, 0, 3);
		pane.add(new Label("Phone:"), 0, 4);
		pane.add(phoneField, 0, 5);
		pane.add(new Label("Address:"), 0, 6);
		pane.add(addressField, 0, 7);
		pane.add(button, 0, 8);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			int id = cell.getID();
			String email = emailField.getText();
			String phone = phoneField.getText();
			String name = nameField.getText();
			String address = addressField.getText();
			
			String message = customerController.editCustomer(name, email, phone, address, id);
			
			lv.refresh();
			window.close();
			
			setList();
			
			if (message.contains("successfully"))
				Popup.displaySuccessMessage(message);
			else
				Popup.displayErrorMessage(message);
		});
		window.setTitle("Edit " + cell.getID());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		String message = customerController.deleteCustomer(cell.getID(), cell.getName());
		lv.refresh();
				
		setList();
		
		Popup.displayErrorMessage(message);
	}
	
	private class Cell extends HBox {
		Label phoneLabel = new Label();
		Label nameLabel = new Label();
		Label emailLabel = new Label();
		Button editButton = new Button();
		Button removeButton = new Button();
		int id;
		String name;
		String email;
		String phone;
		String address;
		
		Cell(int id, String name, String email, String phone, String address) {
			super();
			
			this.address = address;
			this.id = id;
			
			this.name = name;
			nameLabel.setText("" + name);
			nameLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(nameLabel, Priority.ALWAYS);
			
			this.phone = phone;
			phoneLabel.setText("" + phone);
			phoneLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(phoneLabel, Priority.ALWAYS);
			
			this.email = email;
			emailLabel.setText("" + email);
			emailLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(emailLabel, Priority.ALWAYS);
			
			editButton.setGraphic(new ImageView(new Image("view/images/edit.png")));
			editButton.setTooltip(new Tooltip("Edit " + name));
			editButton.setOnAction(e -> {
				if (loggedInUser.getStatus().equalsIgnoreCase("super_admin") || loggedInUser.getStatus().equalsIgnoreCase("admin"))
					edit(this);
				else
					Popup.displayErrorMessage("You do not have permission to edit customers");
			});
			
			removeButton.setGraphic(new ImageView(new Image("view/images/remove.png")));
			removeButton.setTooltip(new Tooltip("Remove " + name));
			removeButton.setOnAction(e -> {
				if (loggedInUser.getStatus().equalsIgnoreCase("super_admin"))
					remove(this);
				else
					Popup.displayErrorMessage("You do not have permission to delete customers");
			});
			
			this.getChildren().addAll(nameLabel, emailLabel, phoneLabel, editButton, removeButton);
		}
		
		Cell() {}

		public int getID() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getPhone() {
			return phone;
		}


		public String getAddress() {
			return address;
		}
	}
}
