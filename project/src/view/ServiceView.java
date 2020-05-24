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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.Service;

public class ServiceView {
	
	private ArrayList<Cell> list;
	private ListView<Cell> lv;
	private ServiceController serviceController;
	
	public ServiceView() {
		serviceController = new ServiceController();
		list = new ArrayList<Cell>();
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
		ArrayList<Service> allServices = serviceController.getAllServices("company"); // TODO get company from logged in user
		
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
			
			String companyName = "company"; // TODO and company from logged in user
			String title = titleField.getText();
			String description = descField.getText();
			double price = Double.parseDouble(priceField.getText());
			
			lv.refresh();
			window.close();
			
			String message = serviceController.newService(companyName, title, description, price);
			Popup.display(message);
			
			setList();
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
			String companyName = "company"; //TODO get company from logged in user
			String title = titleField.getText();
			String description = descField.getText();
			double price = Double.parseDouble(priceField.getText());
		
			lv.refresh();
			window.close();
			
			String message = serviceController.editService(companyName, title, description, price, id);
			Popup.display(message);
			
			setList();
			
		});
		window.setTitle("Edit " + cell.getID());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		String message = serviceController.deleteService(cell.getID(), cell.getTitle());
		Popup.display(message);
		lv.refresh();
		
		setList();
	}
	
	public class Cell extends HBox {
		Label idLabel = new Label();
		Label titleLabel = new Label();
		Label priceLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		int id;
		double price;
		String title;
		String company;
		String description;

		Cell(int id, String title, double price, String description) {
			super();
			
			this.description = description;
			
			this.id = id;
			idLabel.setText("Service id: " + id);
			idLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(idLabel, Priority.ALWAYS);
			
			this.title = title;
			titleLabel.setText("Title: " + title);
			titleLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(titleLabel, Priority.ALWAYS);
			
			this.price = price;
			priceLabel.setText("Price: " + price);
			priceLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(priceLabel, Priority.ALWAYS);
			
			editButton.setOnAction(e -> {
				edit(this);
			});
			
			removeButton.setOnAction(e -> {
				remove(this);
			});
			
			this.getChildren().addAll(idLabel, titleLabel, priceLabel, editButton, removeButton);
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
