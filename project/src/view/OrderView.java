package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OrderView implements IView {

	
	Pane pane = new Pane();

	@Override
	public Node getCenter() {
		return pane;
	}

	@Override
	public VBox getBar() {
		String[] buttonText = {"List", "Create", "Edit", "Remove"};
		Button[] buttons = new Button[buttonText.length];
		VBox vBox = new VBox();
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(buttonText[i]);
			buttons[i].prefWidthProperty().bind(vBox.widthProperty());
		}
		
		buttons[0].setOnAction(e -> {
			listPane();
		});
		
		vBox.getChildren().addAll(buttons);
		return vBox;
	}
	
	private void listPane() {
		pane.getChildren().clear();
		pane.getChildren().add(new Text("list"));
	}
}
