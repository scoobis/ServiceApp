package view;

import java.io.IOException;

import controller.EmployeeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
		window.getIcons().add(new Image("view/images/logo.png"));
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25,25,25,25));
		pane.setMinHeight(300);
		pane.setMinWidth(400);
		
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
	
		
		//Button registerButton = new Button();
		//registerButton.setText("Register");
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
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Login Failed");
					alert.setHeaderText(null);
					alert.setContentText("Wrong password or username");
					alert.showAndWait();
				}
					
			});
		
		registerButton.setOnAction(e -> {
			new RegisterView().render(stage);
		});
		
		Scene scene = new Scene(pane);
		
		
		window.setScene(scene);
	    window.setMaximized(false);
	    window.setTitle("Service Application");
	    window.show();
		
	}
}
