package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ServiceView implements IView {
	
	public String[] buttons = {"List", "Create", "Edit", "Remove"};
	
	private int lastPressed = 0;
	
	public String[] getButtons() {
		return buttons;
	}

	public GridPane getPane(int value) {
		GridPane pane = new GridPane();
		pane.getChildren().clear();
		switch(value) {
		case 0: //list
			pane.getChildren().add(new Text("fjnds"));
			break;
		case 1:
		case 2:
		case 3:
			pane.getChildren().add(new Text("Service " + buttons[value]));
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
