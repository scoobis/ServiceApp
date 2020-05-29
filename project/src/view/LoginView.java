package view;

import controller.EmployeeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Employee;
import security.PasswordHasher;


public class LoginView {
	private Stage window = null;
	private EmployeeController employeeController;
	private MainView mainView;
	
	private final int WIDTH = 400;
	private final int HEIGHT = 300;
	
	public LoginView() {
		employeeController = new EmployeeController();
		mainView = new MainView();
	}
	
	public void render(Stage stage) {
		window = stage;
		window.getIcons().add(new Image("view/images/logo.png"));
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25,25,25,25));
		pane.setMinHeight(HEIGHT);
		pane.setMinWidth(WIDTH);
		pane.setMaxHeight(HEIGHT);
		pane.setMaxWidth(WIDTH);
		
		Label username = new Label("Email");
		pane.add(username, 0, 1);
		
		TextField usernameField = new TextField();
		pane.add(usernameField, 0, 2);
		
		Label password = new Label("Password");
		pane.add(password, 0, 3);
		
		PasswordField passwordField = new PasswordField();
		pane.add(passwordField, 0, 4);
		
		Button loginButton = new Button();
		loginButton.setText("Login");
	
		Hyperlink registerButton = new Hyperlink("Register account.");
		TextFlow flow = new TextFlow(
			    new Text("Don't have an account? "), registerButton
			);
		
		HBox.setHgrow(loginButton, Priority.ALWAYS);
		loginButton.prefWidthProperty().bind(pane.widthProperty().multiply(.8));
		loginButton.getStylesheets().add("view/css/login-button.css");
		pane.add(loginButton, 0, 5);
		pane.add(flow, 0, 6);
		
		CheckBox isSuperAdmin = new CheckBox();
		isSuperAdmin.setText("Are you SuperAdmin?");
		pane.add(isSuperAdmin, 0, 7);
		
		Employee test;
			test = Employee.getLoggedInUser();
			if (test != null) {
				usernameField.setText(test.getEmail());
			}
			loginButton.setOnAction(e -> {
				
				Employee employee = employeeController.login(usernameField.getText(), PasswordHasher.hashPassword(passwordField.getText()), isSuperAdmin.isSelected());
				if(employee != null) {
					Employee.logInUser(employee);
					mainView.render(stage);
				} else {
					Popup.displayErrorMessage("Wrong password or username \n \n If you are super admin make sure to check the box below!");
				}
					
			});
		
		registerButton.setOnAction(e -> {
			new RegisterView().render();
		});
		
		Scene scene = new Scene(pane);
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    window.setX((screenBounds.getWidth() - WIDTH) / 2); 
	    window.setY((screenBounds.getHeight() - HEIGHT) / 2); 
		
		window.setScene(scene);
	    window.setMaximized(false);
	    window.setTitle("Service Application");
	    window.show();
		
	}
}
