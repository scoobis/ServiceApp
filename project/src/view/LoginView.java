package view;

import controller.EmployeeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Employee;
import security.PasswordHasher;


public class LoginView {
	private Stage window = null;
	private EmployeeController employeeController;
	private MainView mainView;
	
	public LoginView() {
		employeeController = new EmployeeController();
		mainView = new MainView();
	}
	
	public void render(Stage stage) {
		window = stage;
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25,25,25,25));
		pane.setMinHeight(900);
		pane.setMinWidth(1800);
		
		Label username = new Label("Username");
		pane.add(username, 0, 1);
		
		TextField usernameField = new TextField();
		pane.add(usernameField, 0, 2);
		
		Label password = new Label("Password");
		pane.add(password, 0, 3);
		
		PasswordField passwordField = new PasswordField();
		pane.add(passwordField, 0, 4);
		
		Button loginButton = new Button();
		loginButton.setText("Login");
	
		
		Button registerButton = new Button();
		registerButton.setText("Register");
		
		HBox boxButtons = new HBox();
		
		Button[] buttons = new Button[2];
		buttons[0] = loginButton;
		buttons[1] = registerButton;
		boxButtons.setMargin(buttons[1], new Insets(0,0,0,50));
		boxButtons.getChildren().addAll(buttons);
		pane.add(boxButtons, 0, 5);
		
		Employee test = Employee.getLoggedInUser();
		if (test != null) {
			usernameField.setText(test.getEmail());
		}
		
		buttons[0].setOnAction(e -> {
			
			Employee employee = employeeController.login(usernameField.getText(), PasswordHasher.hashPassword(passwordField.getText()));
			if(employee != null) {
				Employee.logInUser(employee);
				mainView.render(stage);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Login Failed");
				alert.setHeaderText(null);
				alert.setContentText("Wrong password or username");
				alert.showAndWait();
			}
				
		});
		
		buttons[1].setOnAction(e -> {
			new RegisterView().render(stage);
		});
		
		Scene scene = new Scene(pane);
		
		
		window.setScene(scene);
	    window.setMaximized(false);
	    window.setTitle("Service Application");
	    window.show();
		
	}
}
