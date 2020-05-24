package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Popup {
	
	//TODO Make this look nicer
	public static void display(String message) {
		GridPane pane = new GridPane();
		Button button = new Button("Close");
		Scene scene = new Scene(pane, 300, 150);
		Stage window = new Stage();
		
		pane.add(new Label(message), 0, 0);
		pane.add(button, 0, 1);
		
		window.setTitle("Message");
		window.setScene(scene);
		window.show();
		
		button.setOnAction(e -> window.close());
	}
	
}
