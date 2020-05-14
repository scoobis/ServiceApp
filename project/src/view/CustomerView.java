package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.EmployeeView.Cell;

public class CustomerView {
	
	private ArrayList<Cell> list = new ArrayList<Cell>();
	private ObservableList<Cell> obsList;
	private ListView<Cell> lv;
	
	public BorderPane getCenter() {
		//TODO Put info from database into "list" here instead of random loop
		for(int i = 0; i < 100; i++) {
			list.add(new Cell(Integer.toString(i), "name123", "email123", "phone123", "address123"));
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
		TextField idField = new TextField();
		TextField nameField = new TextField();
		TextField emailField = new TextField();
		TextField phoneField = new TextField();
		TextField addressField = new TextField();
		
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
			Cell cell = new Cell(idField.getText(), nameField.getText(), emailField.getText(), 
								 phoneField.getText(), addressField.getText());
			obsList.add(cell);
			window.close();
			//TODO Call controller here
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
			Cell newCell = new Cell(idField.getText(), nameField.getText(), emailField.getText(), 
									phoneField.getText(), addressField.getText());
			obsList.set(obsList.indexOf(cell), newCell);
			lv.refresh();
			window.close();
			//TODO Call controller here
		});
		window.setTitle("Edit " + cell.getID());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO Call controller here
		obsList.remove(cell);
	}
	
	public class Cell extends HBox {
		Label idLabel = new Label();
		Label nameLabel = new Label();
		Label emailLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		String id;
		String name;
		String email;
		String phone;
		String address;

		Cell(String id, String name, String email, String phone, String address) {
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
			emailLabel.setText("Shop Id: " + email);
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

		public String getID() {
			return id;
		}

		public void setID(String id) {
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
