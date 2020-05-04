package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView implements IView {
	
	Stage window = null;

	@Override
	public void render(Stage primaryStage) {
		window = primaryStage;
		
		HBox layout = new HBox(10);
		Button b1 = new Button();
		b1.setText("order");
		
		b1.setOnAction(e -> {
			
		//new OrderView().render();
		System.out.println("to order");
	});
		
		Button b2 = new Button();
		b2.setText("btn1");
		
		Button b3 = new Button();
		b3.setText("btn1");
		
		Button b4 = new Button();
		b4.setText("btn1");
		
		layout.getChildren().addAll(b1, b2, b3, b4);
		Scene scene = new Scene(layout, 1800, 850);
		
		window.setScene(scene);
	    window.setMaximized(true);
	    window.setTitle("Service Application");
	    window.show();
	}
	
}
