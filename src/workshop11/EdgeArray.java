package workshop11;

import java.util.ArrayList;

import workshop11.Edge;

// Collection of edges
public class EdgeArray {
	private ArrayList<Edge>  edgeArray;
	EdgeArray() {
		edgeArray = new ArrayList<Edge>();
	}
	void add(Edge edge) {
		edgeArray.add(edge);
	}
	int  size()     { return edgeArray.size(); }
	Edge get(int i) { return edgeArray.get(i); } 
}
