import javafx.application.Application;
import javafx.stage.Stage;
import model.Employee;
import model.User;
import model.database.CreateTables;
import model.database.EmployeeDatabase;
import view.LoginView;


public class Main extends Application {
	
	public static void main(String[] args) {
		
		// Create new tables (if it does not already exists)
		CreateTables newTables = new CreateTables();
		newTables.executeCreateAllTables();
		
		//EmployeeDatabase d = new EmployeeDatabase();
		//System.out.println(d.getAllEmployees("company"));
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//MainView mainView = new MainView();
		//mainView.render(primaryStage);
		LoginView log = new LoginView();
		log.render(primaryStage);
	}
}
