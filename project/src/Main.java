import javafx.application.Application;
import javafx.stage.Stage;
import model.Order;
import model.User;
import model.database.CreateTables;
import model.database.EmployeeDatabase;
import view.MainView;

public class Main extends Application {
	
	public static void main(String[] args) {
		
		// Create new tables (if it does not already exists)
		CreateTables newTables = new CreateTables();
		newTables.executeCreateAllTables();
		
		EmployeeDatabase d = new EmployeeDatabase();
		d.getAllEmployees("company");
		
		User u = new User("phonees", "email", "name", "company", 5, "User");
		//d.saveEmployee(u);
		
		System.out.println(d.validateEmployee("email", "password"));
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//MainView mainView = new MainView();
		//mainView.render(primaryStage);
	}
}
