import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.database.CreateTables;
import model.database.EmployeeDatabase;

public class Main extends Application {
	
	Stage window;
	
	public static void main(String[] args) {
		
		// Create new tables (if it does not already exists)
		CreateTables newTables = new CreateTables();
		newTables.executeCreateAllTables();
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		StackPane layout = new StackPane();
		Scene scene = new Scene(layout, 1800, 850);
		
		window.setScene(scene);
	    window.setMaximized(true);
	    window.setTitle("Service Application");
	    window.show();
		
	}
}
