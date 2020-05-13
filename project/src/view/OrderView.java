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
		for(int i = 0; i < 100; i++) {
			list.add(new Cell(i, ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE)));
		}
		
		BorderPane bp = new BorderPane();
		
		lv = new ListView<Cell>();
		obsList = FXCollections.observableList(list);
		lv.setItems(obsList);
		bp.setCenter(lv);
		return bp;
	}
	
	private void edit(Cell cell) {
		GridPane pane = new GridPane();
		Button button = new Button("Edit");
		TextField orderIdField = new TextField("" + cell.getOrderId());
		TextField customerIdField = new TextField("" + cell.getCustomerId());
		
		pane.add(new Label("Order Id:"), 0, 0);
		pane.add(orderIdField, 0, 1);
		pane.add(new Label("Customer Id:"), 0, 2);
		pane.add(customerIdField, 0, 3);
		pane.add(button, 0, 4);
		
		Scene scene = new Scene(pane, 300, 600);
		Stage window = new Stage();
		
		button.setOnAction(e -> {
			Cell newCell = new Cell(Integer.parseInt(orderIdField.getText()), Integer.parseInt(customerIdField.getText()));
			obsList.set(obsList.indexOf(cell), newCell);
			lv.refresh();
			window.close();
			//TODO Call controller here
		});
		window.setTitle("Edit " + cell.getOrderId());
		window.setScene(scene);
		window.show();
	}
	
	private void remove(Cell cell) {
		//TODO Call controller here
		obsList.remove(cell);
	}
	
	public class Cell extends HBox {
		Label orderLabel = new Label();
		Label customerLabel = new Label();
		Button editButton = new Button("Edit");
		Button removeButton = new Button("Remove");
		int orderId;
		int customerId;
		
		Cell(int orderId, int customerId) {
			super();
			
			this.orderId = orderId;
			orderLabel.setText("Order id: " + orderId);
			orderLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(orderLabel, Priority.ALWAYS);
			
			this.customerId = customerId;
			customerLabel.setText("Customer id: " + customerId);
			customerLabel.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(customerLabel, Priority.ALWAYS);
			
			editButton.setOnAction(e -> {
				edit(this);
			});
			
			removeButton.setOnAction(e -> {
				remove(this);
			});
			
			this.getChildren().addAll(orderLabel, customerLabel, editButton, removeButton);
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		
	}
}
