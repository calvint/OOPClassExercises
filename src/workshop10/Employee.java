package workshop10;

import java.util.Iterator;
import java.util.ArrayList;

public class Employee extends Worker {
 
	String name;
    
	public Employee(String name) 
	{ 
		this.name = name;
	}
  
	public String getName() {
		return name;
	}

	public Iterator createIterator() {
		return new NullIterator();
	}
 
	public void print() {
		System.out.print("  " + getName());
	}
}
