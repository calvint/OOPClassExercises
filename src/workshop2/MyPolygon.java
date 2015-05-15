package workshop2;

import java.util.ArrayList;
import java.util.List;

public class MyPolygon {
	private ArrayList<xyPair> coordinates;
	
	public MyPolygon(ArrayList<xyPair> pairList){
		coordinates = new ArrayList<xyPair>();
		for (xyPair xyPair : pairList) {
			coordinates.add(xyPair);
		}
	}
	
	public void addCoordiante(double d, double e) {
		coordinates.add(new xyPair(d, e));
	}
	
	public int size() {
		return coordinates.size();
	}
	
	public void printCoordinates() {
		System.out.println("Coordinates for polygon: ");
		for (xyPair pair: coordinates) {
			System.out.println("    x: " + pair.getX() + " y: " + pair.getY());
		}
	}
}
