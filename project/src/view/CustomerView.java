package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomerView implements IView {
	
	@Override
	public Node getCenter() {
		return new Text("Customer");
	}

	@Override
	public VBox getBar() {
		String[] buttonText = {"Create", "Edit", "Remove", "List"};
		Button[] buttons = new Button[buttonText.length];
		VBox vBox = new VBox();
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(buttonText[i]);
			buttons[i].prefWidthProperty().bind(vBox.widthProperty());
		}
		
		vBox.getChildren().addAll(buttons);
		
		return vBox;
	}
}
