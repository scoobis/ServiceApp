package model.database;

import java.util.ArrayList;
import java.util.Observer;

import model.Employee;

public class EmployeeDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {

	public ArrayList<Employee> getAllEmployees() {
		return null;
	}
	
	public Employee getEmployeeById(String id) {
		return null;
	}
	
	public void saveEmployee(Employee e) {
		
	}
	
	public int deleteEmployee(String id) {
		return -1;
	}
	
	public Employee editEmployee(String id, Employee e) {
		return null;
	}
	
	public boolean validateEmployee(String email, String password) {
		return false;
	}
	
	@Override
	public void attach(Observer o) {
		
	}

	@Override
	public void detach(Observer o) {
		
	}

	@Override
	public void update() {
		
	}

}
