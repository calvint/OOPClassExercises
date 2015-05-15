package workshop10;
import java.util.*;

public class CompositeIterator implements Iterator {
	Stack stack = new Stack();
	Worker employee;
	Iterator iterator = null;
   
	public CompositeIterator(Worker record) {
		employee = record;
	}

	public CompositeIterator(Iterator iterator) {
		this.iterator = iterator;
		employee = (Worker) iterator.next();
	}
   
	public Object next() {
		Worker retVal = employee;
		while (!stack.empty() && !iterator.hasNext()) {
			iterator = (Iterator) stack.pop();
		}
		if ( iterator.hasNext() || !stack.isEmpty() || employee instanceof Manager) {
			if (employee instanceof Manager) {
				stack.push(iterator);
				iterator = employee.createIterator();
				if (iterator instanceof CompositeIterator) {
					employee = ((CompositeIterator)iterator).employee;
					iterator = ((CompositeIterator)iterator).iterator;
				}
				else {
					employee = (Worker) iterator.next();
				}
			} else { 
				employee = (Worker) iterator.next();
			}
		} else { // iterator is null
			employee = null;
		}
		
		return retVal;
	}
  
	public boolean hasNext() {
		return employee != null;
	}
   
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	public int getDepth() {
		return stack.size();
	}
}

