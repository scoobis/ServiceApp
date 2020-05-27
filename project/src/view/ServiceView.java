package view;

import java.util.ArrayList;

import controller.ServiceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Employee;
import model.Service;

public class ServiceView {
	
	private ArrayList<Cell> list;
	private ListView<Cell> lv;
	private ServiceController serviceController;
	
	private Employee loggedInUser;
	
	public ServiceView() {
		serviceController = new ServiceController();
		list = new ArrayList<Cell>();
	}
	
	public BorderPane getCenter() {
		ObservableList<Cell> obsList;
		
		loggedInUser = Employee.getLoggedInUser();
		
		setList();
		
		BorderPane bp = new BorderPane();
		
		Button createButton = new Button();
		createButton.setGraphic(new ImageView(new Image("view/images/create.png")));
		createButton.setTooltip(new Tooltip("Create new service"));
		
		createButton.setOnAction(e -> {
			if (loggedInUser.getStatus().equalsIgnoreCase("admin") || loggedInUser.getStatus().equalsIgnoreCase("super_admin"))
				create();
			else
				Popup.displayErrorMessage("You do not have permission to create services!");
		});
	
		bp.getStylesheets().add("view/css/list-buttons.css");
		lv = new ListView<Cell>();
		obsList = FXCollections.observableList(list);
		lv.setItems(obsList);
		bp.setCenter(lv);
		bp.setTop(createButton);
		return bp;
	}
	
	private void setList() {
		ArrayList<Service> allServices = serviceController.getAllServices(loggedInUser.getCompanyName());
		
		list.clear();
		
		for(Service service : allServices) {
			list.add(new Cell(service.getId(), service.getTitle(), service.getPrice(), service.getDescription()));
		}
	}
	
	private void create() {
		GridPane pane = new GridPane();
		Button button = new Button("Create");
		TextField titleField = new TextField();
		DoubleTextField priceField = new DoubleTextField();
		TextArea descField = new TextArea();
		
		pane.add(new Label("Title:"), 0, 0);
		pane.add(titleField, 0, 1);
		pane.add(new Label("Price:"), 0, 2);
		pane.add(priceField, 0, 3);
		pane.add(new Label("Description:"), 0, 4);
		pane.add(descField, 0, 5);
		pane.add(button, 0, 6);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			String companyName = loggedInUser.getCompanyName();
			String title = titleField.getText();
			String description = descField.getText();
			double price = -1;
			if(priceField.getText().length() != 0)
				price = Double.parseDouble(priceField.getText());
			
			lv.refresh();
			window.close();
			
			String message = serviceController.newService(companyName, title, description, price);
			
			setList();
			
			if (message.contains("successfully"))
				Popup.displaySuccessMessage(message);
			else
				Popup.displayErrorMessage(message);
		});
		
		window.setTitle("Create new order");
		window.setScene(scene);
		window.show();
	}
	
	private void edit(Cell cell) {
		GridPane pane = new GridPane();
		Button button = new Button("Edit");
		TextField titleField = new TextField("" + cell.getTitle());
		DoubleTextField priceField = new DoubleTextField("" + cell.getPrice());
		TextArea descField = new TextArea("" + cell.getDescription());
		
		pane.add(new Label("Title:"), 0, 0);
		pane.add(titleField, 0, 1);
		pane.add(new Label("Price:"), 0, 2);
		pane.add(priceField, 0, 3);
		pane.add(new Label("Description:"), 0, 4);
		pane.add(descField, 0, 5);
		pane.add(button, 0, 6);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			
			int id = cell.getID();
			String companyName = loggedInUser.getCompanyName();
			String title = titleField.getText();
			String description = descField.getText();
			double price = Double.parseDouble(priceField.getText());
		
			lv.refresh();
			window.close();
			
			String message = serviceController.editService(companyName, title, description, price, id);
			
			if (message.contains("successfully"))
				Popup.displaySuccessMessage(message);
			else
				Popup.displayErrorMessage(message);
			
			setList();
			
		});
		window.setTitle("Edit " + cell.getID());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		String message = serviceController.deleteService(cell.getID(), cell.getTitle());
		lv.refresh();
		
		setList();
		
		Popup.displayErrorMessage(message);
	}
	
	public class Cell extends HBox {
		private Label titleLabel = new Label();
		private Label priceLabel = new Label();
		private Button editButton = new Button();
		private Button removeButton = new Button();
		private int id;
		private double price;
		private String title;
		private String company;
		private String description;

		Cell(int id, String title, double price, String description) {
			super();
			
			this.id = id;
			this.description = description;
			
			this.title = title;
			titleLabel.setText("" + title);
			titleLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(titleLabel, Priority.ALWAYS);
			
			this.price = price;
			priceLabel.setText("" + price);
			priceLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(priceLabel, Priority.ALWAYS);
			
			editButton.setGraphic(new ImageView(new Image("view/images/edit.png")));
			editButton.setTooltip(new Tooltip("Edit " + title));
			editButton.setOnAction(e -> {
				if (loggedInUser.getStatus().equalsIgnoreCase("admin") || loggedInUser.getStatus().equalsIgnoreCase("super_admin"))
					edit(this);
				else
					Popup.displayErrorMessage("You do not have permission to edit services!");
			});
			
			removeButton.setGraphic(new ImageView(new Image("view/images/remove.png")));
			removeButton.setTooltip(new Tooltip("Remove " + title));
			removeButton.setOnAction(e -> {
				if (loggedInUser.getStatus().equalsIgnoreCase("super_admin"))
					remove(this);
				else
					Popup.displayErrorMessage("You do not have permission to remove services!");
			});
			
			this.getChildren().addAll(titleLabel, priceLabel, editButton, removeButton);
		}

		public int getID() { return id; }


		public void setId(int id) { this.id = id; }


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public double getPrice() {
			return price;
		}


		public void setPrice(int price) {
			this.price = price;
		}


		public String getCompany() {
			return company;
		}


		public void setCompany(String company) {
			this.company = company;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}
		
	}
}
