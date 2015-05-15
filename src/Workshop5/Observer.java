package Workshop5;

import java.util.ArrayList;

public interface Observer {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void update();
}
