package model.database;

import java.util.Observer;

public interface DatabaseSubject {

	public void attach(Observer o);
	public void detach(Observer o);
	
}
