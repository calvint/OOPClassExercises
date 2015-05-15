package workshop10;

import java.util.Iterator;
import java.util.ArrayList;


public class Manager extends Worker {
 
	ArrayList workerComponents = new ArrayList();
	String name;
  
	public Manager(String name) {
		this.name = name;
	}
 
	public void add(Worker workerComponent) {
		workerComponents.add(workerComponent);
	}
 
	public void remove(Worker workerComponent) {
		workerComponents.remove(workerComponent);
	}
 
	public Worker getChild(int i) {
		return (Worker)workerComponents.get(i);
	}
 
	public String getName() {
		return name;
	}
  
	public Iterator createIterator() {
		return workerComponents.iterator();
	}
	
	public void print() {
		System.out.print("\n" + getName());
		Iterator iterator = workerComponents.iterator();
		while (iterator.hasNext()) {
			Worker menuComponent = 
				(Worker)iterator.next();
			menuComponent.print();
		}
	}
}
