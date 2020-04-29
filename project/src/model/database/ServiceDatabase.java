package model.database;

import java.util.ArrayList;
import java.util.Observer;

import model.Service;

public class ServiceDatabase implements DatabaseConnector, DatabaseObserver, DatabaseSubject {

	public ArrayList<Service> getAllServices() {
		return null;
	}
	
	public Service getServiceById(String id) {
		return null;
	}
	
	public void saveService(Service s) {
		
	}
	
	public int deleteService(String id) {
		return -1;
	}
	
	public Service editService(String id, Service s) {
		return null;
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
