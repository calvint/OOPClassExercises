package workshop3;

import java.util.ArrayList;

public class MyRedBlackPath {
	ArrayList<xyPair> list = new ArrayList<xyPair>();
	
	public void insertNode(xyPair pair) {
		list.add(pair);
	}

	public void printPath() {
		System.out.println("printing RB path");
		for (xyPair pair: list) {
			System.out.println(pair.toString());
		}
	}
}
