package view;

import controller.EmployeeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class RegisterView {
	
	private final int WIDTH = 300;
	private final int HEIGHT = 600;
	
	public void render() {
		GridPane pane = new GridPane();
		pane.setMinHeight(HEIGHT);
		pane.setMinWidth(WIDTH);
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25,25,25,25));
		
		Label email = new Label("Email");
		pane.add(email, 0, 1);
		
		TextField emailField = new TextField();
		pane.add(emailField, 0, 2);
		
		Label company = new Label("Company Name");
		pane.add(company, 0, 3);
		TextField companyField = new TextField();
		pane.add(companyField, 0, 4);
		
		Label name = new Label("Name");
		pane.add(name, 0, 5);
		TextField nameField = new TextField();
		pane.add(nameField, 0, 6);
		
		Label phone = new Label("Phone");
		pane.add(phone, 0, 7);
		TextField phoneField = new TextField();
		pane.add(phoneField, 0, 8);
		
		Label password = new Label("Password");
		pane.add(password, 0, 9);
		PasswordField passwordField = new PasswordField();
		pane.add(passwordField, 0, 10);
		
		Label passwordConfirm = new Label("Confirm password");
		pane.add(passwordConfirm, 0, 11);
		PasswordField passwordConfirmField = new PasswordField();
		pane.add(passwordConfirmField, 0, 12);
		
		Button registerButton = new Button();
		registerButton.setText("Register");
		
		
		HBox boxButtons = new HBox();

		boxButtons.getChildren().add(registerButton);
		pane.add(boxButtons, 0, 13);
		
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		Stage window = new Stage();
		
		registerButton.setOnAction(e -> {
				if(passwordField.getText().equals(passwordConfirmField.getText())) {
					
					EmployeeController cont = new EmployeeController();
					String message = cont.newSuperAdmin(nameField.getText(), emailField.getText(), phoneField.getText(), passwordField.getText(), companyField.getText());
					
					if (message.contains("successfully")) {
						window.close();
						Popup.displaySuccessMessage(message);
					}
					else
						Popup.displayErrorMessage(message);
				}
				else {
					Popup.displayErrorMessage("Password don't match");
				}
		});
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    window.setX((screenBounds.getWidth() - WIDTH) / 2); 
	    window.setY((screenBounds.getHeight() - HEIGHT) / 2); 
		
		window.setScene(scene);
	    window.setMaximized(false);
	    window.setTitle("Service Application");
	    window.show();	
	}
}
