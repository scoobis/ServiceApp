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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView implements IView {
	
	Stage window = null;
	private final int WIDTH = 1800;
	private final int HEIGHT = 900;
	private String[] menuText = {"Home", "Orders", "Services", "Customer", "Employees"};
	
	public void render(Stage primaryStage) {
		window = primaryStage;
		
		BorderPane pane = new BorderPane();
		
		Button[] menuButtons = new Button[menuText.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new Button(menuText[i]);
		}
		
		menuButtons[0].setOnAction(e -> {
			pane.setCenter(getCenter());
			pane.setLeft(setupVBox(getBar(), pane));
		});
		menuButtons[1].setOnAction(e -> {
			pane.setCenter(new OrderView().getCenter());
			pane.setLeft(setupVBox(new OrderView().getBar(), pane));
		});
		menuButtons[2].setOnAction(e -> {
			pane.setCenter(new ServiceView().getCenter());
			pane.setLeft(setupVBox(new ServiceView().getBar(), pane));
		});
		menuButtons[3].setOnAction(e -> {
			pane.setCenter(new CustomerView().getCenter());
			pane.setLeft(setupVBox(new CustomerView().getBar(), pane));
		});
		menuButtons[4].setOnAction(e -> {
			pane.setCenter(new EmployeeView().getCenter());
			pane.setLeft(setupVBox(new EmployeeView().getBar(), pane));
		});
		
		ToolBar toolBarH = new ToolBar();
		toolBarH.getItems().addAll(menuButtons);
		toolBarH.setOrientation(Orientation.HORIZONTAL);
		
		Text text = new Text("Home");
		
		pane.setCenter(text);
		pane.setTop(toolBarH);
		pane.setLeft(setupVBox(getBar(), pane));
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		
		window.setScene(scene);
	    window.setMaximized(false);
	    window.setTitle("Service Application");
	    window.show();
	}
	
	@Override
	public Node getCenter() {
		return new Text("Home");
	}

	@Override
	public VBox getBar() {
		String[] buttonText = {"Homepage", "Information", "Help", "Settings"};
		Button[] buttons = new Button[buttonText.length];
		VBox vBox = new VBox();
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(buttonText[i]);
			buttons[i].prefWidthProperty().bind(vBox.widthProperty());
		}
		
		vBox.getChildren().addAll(buttons);
		return vBox;
	}
	
	private VBox setupVBox(VBox vb, Pane pane) {
		VBox v = new VBox(vb);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(0, 0, HEIGHT / 1.75, 0));
		for(Node n : vb.getChildren())
			if(n instanceof Button) 
				((Button) n).prefHeightProperty().bind(v.heightProperty());
		
		v.prefWidthProperty().bind(pane.widthProperty().multiply(.15));
		v.prefHeightProperty().bind(pane.heightProperty());
		
		Text text = new Text("Text here ...");
		text.setFont(new Font(20));
		v.getChildren().add(text);
		
		v.getStylesheets().add("view/css/buttons.css");
		return v;
	}
}
