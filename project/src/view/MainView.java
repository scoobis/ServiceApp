package view;

import java.io.FileInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView {
	
	Stage window = null;
	private final int WIDTH = 1800;
	private final int HEIGHT = 900;
	private String[] menuText = {"Home", "Order", "Service", "Customer", "Employee"};
	private int lastPressed = 0;
	
	HomeView hv = new HomeView();
	OrderView ov = new OrderView();
	EmployeeView ev = new EmployeeView();
	CustomerView cv = new CustomerView();
	ServiceView sv = new ServiceView();
	
	public void render(Stage primaryStage) {
		window = primaryStage;
		
		BorderPane pane = new BorderPane();
		
		Button[] menuButtons = new Button[menuText.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new Button(menuText[i]);
		}
		
		menuButtons[0].setOnAction(e -> {
			if(lastPressed != 0) {
				pane.setCenter(hv.getCenter());
				lastPressed = 0;
			}
		});
		menuButtons[1].setOnAction(e -> {
			if(lastPressed != 1) {
				pane.setCenter(ov.getCenter());
				lastPressed = 1;
			}
		});
		menuButtons[2].setOnAction(e -> {
			if(lastPressed != 2) {
				pane.setCenter(sv.getCenter());
				lastPressed = 2;
			}
		});
		menuButtons[3].setOnAction(e -> {
			if(lastPressed != 3) {
				pane.setCenter(cv.getCenter());
				lastPressed = 3;
			}
		});
		menuButtons[4].setOnAction(e -> {
			if(lastPressed != 4) {
				pane.setCenter(ev.getCenter());
				lastPressed = 4;
			}
		});
		
		ToolBar toolBarH = new ToolBar();
		toolBarH.getItems().addAll(menuButtons);
		toolBarH.setOrientation(Orientation.HORIZONTAL);
		
		pane.setCenter(hv.getCenter());
		pane.setTop(toolBarH);
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		
		window.setScene(scene);
	    window.setMaximized(false);
	    window.setTitle("Service Application");
	    window.show();
	}
}
