package workshop10;

import java.util.*;

public abstract class Worker {
   
	public void add(Worker menuComponent) {
		throw new UnsupportedOperationException();
	}
	public void remove(Worker menuComponent) {
		throw new UnsupportedOperationException();
	}
	public Worker getChild(int i) {
		throw new UnsupportedOperationException();
	}
  
	public String getName() {
		throw new UnsupportedOperationException();
	}
	public abstract Iterator createIterator();
 
	public void print() {
		throw new UnsupportedOperationException();
	}
}
