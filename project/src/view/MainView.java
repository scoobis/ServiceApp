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
			pane.setCenter(hv.getCenter());
		});
		menuButtons[1].setOnAction(e -> {
			pane.setCenter(ov.getCenter());
		});
		menuButtons[2].setOnAction(e -> {
			pane.setCenter(sv.getCenter());
		});
		menuButtons[3].setOnAction(e -> {
			pane.setCenter(cv.getCenter());
		});
		menuButtons[4].setOnAction(e -> {
			pane.setCenter(ev.getCenter());
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
	
	/*
	private VBox setupVBox(VBox vb, Pane pane) {
		VBox v = new VBox(vb);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(0, 0, HEIGHT / 1.75, 0));
		for(Node n : vb.getChildren())
			if(n instanceof Button) {
				((Button) n).prefHeightProperty().bind(v.heightProperty());
			}
		
		v.prefWidthProperty().bind(pane.widthProperty().multiply(.15));
		v.prefHeightProperty().bind(pane.heightProperty());
		
		Text text = new Text("Text here ...");
		text.setFont(new Font(20));
		v.getChildren().add(text);
		
		v.getStylesheets().add("view/css/buttons.css");
		return v;
	}
	*/
}
