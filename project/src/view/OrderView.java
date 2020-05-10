package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OrderView implements IView {

	public String[] buttons = {"List", "Create", "Edit", "Remove"};
	
	private int lastPressed = 0;
	
	public String[] getButtons() {
		return buttons;
	}

	public GridPane getPane(int value) {
		GridPane pane = new GridPane();
		pane.getChildren().clear();
		switch(value) {
		case 0: //List
			pane.getChildren().add(new Text("Order " + buttons[value]));
			break;
		case 1: //Create
			Button create = new Button("Create");
			TextField orderField = new TextField();
			pane.add(orderField, 0, 1);
			pane.add(new Label("Order: "), 0, 0);
			pane.add(create, 0, 6);
			break;
		case 2: //Edit
		case 3: //Remove
			pane.getChildren().add(new Text("Order " + buttons[value]));
			break;
		default:
			break;
		}
		pane.getStylesheets().add("view/css/pane.css");
		return pane;
	}

	public BorderPane getCenter() {
		BorderPane bp = new BorderPane();
		bp.setLeft(getBar(bp));
		bp.setCenter(getPane(0));
		return bp;
	}
	
	public VBox getBar(BorderPane bp) {
		Button[] buttons = new Button[this.buttons.length];
		VBox vBox = new VBox();
		
		vBox.prefWidthProperty().bind(bp.widthProperty().multiply(.15));
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(this.buttons[i]);
			buttons[i].prefWidthProperty().bind(vBox.widthProperty());
			buttons[i].prefHeightProperty().bind(vBox.heightProperty().multiply(.1));
			
			int index = i;
			buttons[i].setOnAction(e -> {
				if(lastPressed != index) {
					bp.setCenter(getPane(index));
					lastPressed = index;
				}
			});
		}
		
		vBox.getChildren().addAll(buttons);
		vBox.getStylesheets().addAll("view/css/buttons.css", "view/css/bar.css");
		return vBox;
	}
}
