package workshop3;

import java.util.ArrayList;

public class MyColoredPaths {
	ArrayList<MyRedBlackPath> list = new ArrayList<MyRedBlackPath>();
	int index = 0;
	
	public MyColoredPaths() {
		list.add(new MyRedBlackPath());
	}
	
	public MyRedBlackPath getCurrentPath() {
		return list.get(index);
	}
	
	public void newPath() {
		list.add(new MyRedBlackPath());
		index ++;
	}
	
	public void printPaths() {
		for (MyRedBlackPath path : list) {
			path.printPath();
		}
	}
}
