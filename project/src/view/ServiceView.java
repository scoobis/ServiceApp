package view;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
import view.OrderView.Cell;

public class ServiceView {
	
	private ArrayList<Cell> list = new ArrayList<Cell>();
	private ObservableList<Cell> obsList;
	private ListView<Cell> lv;
	
	public BorderPane getCenter() {
		//TODO Put info from database into "list" here instead of random loop
		for(int i = 0; i < 100; i++) {
			list.add(new Cell(Integer.toString(i), "title123", ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), "company123", "description123"));
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
		TextField serviceIdField = new TextField();
		TextField titleField = new TextField();
		TextField priceField = new TextField();
		TextField companyField = new TextField();
		TextArea descField = new TextArea();
		
		pane.add(new Label("Service Id:"), 0, 0);
		pane.add(serviceIdField, 0, 1);
		pane.add(new Label("Title:"), 0, 2);
		pane.add(titleField, 0, 3);
		pane.add(new Label("Price:"), 0, 4);
		pane.add(priceField, 0, 5);
		pane.add(new Label("Company:"), 0, 6);
		pane.add(companyField, 0, 7);
		pane.add(new Label("Description:"), 0, 8);
		pane.add(descField, 0, 9);
		pane.add(button, 0, 10);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			Cell cell = new Cell(serviceIdField.getText(), titleField.getText(), Integer.parseInt(priceField.getText()), 
					companyField.getText(), descField.getText());
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
		TextField serviceIdField = new TextField("" + cell.getServiceId());
		TextField titleField = new TextField("" + cell.getTitle());
		TextField priceField = new TextField("" + cell.getPrice());
		TextField companyField = new TextField("" + cell.getCompany());
		TextArea descField = new TextArea("" + cell.getDescription());
		
		pane.add(new Label("Service Id:"), 0, 0);
		pane.add(serviceIdField, 0, 1);
		pane.add(new Label("Title:"), 0, 2);
		pane.add(titleField, 0, 3);
		pane.add(new Label("Price:"), 0, 4);
		pane.add(priceField, 0, 5);
		pane.add(new Label("Company:"), 0, 6);
		pane.add(companyField, 0, 7);
		pane.add(new Label("Description:"), 0, 8);
		pane.add(descField, 0, 9);
		pane.add(button, 0, 10);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			Cell newCell = new Cell(serviceIdField.getText(), titleField.getText(), Integer.parseInt(priceField.getText()), 
									companyField.getText(), descField.getText());
			obsList.set(obsList.indexOf(cell), newCell);
			lv.refresh();
			window.close();
			//TODO Call controller here
		});
		window.setTitle("Edit " + cell.getServiceId());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO Call controller here
		obsList.remove(cell);
	}
	
	public class Cell extends HBox {
		Label idLabel = new Label();
		Label titleLabel = new Label();
		Label priceLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		String serviceId;
		int price;
		String title;
		String company;
		String description;

		Cell(String serviceId, String title, int price, String company, String description) {
			super();
			
			this.company = company;
			this.description = description;
			
			this.serviceId = serviceId;
			idLabel.setText("Service id: " + serviceId);
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


		public String getServiceId() {
			return serviceId;
		}


		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public int getPrice() {
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
