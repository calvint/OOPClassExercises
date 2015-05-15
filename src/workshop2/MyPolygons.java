package workshop2;

import java.util.ArrayList;

public class MyPolygons{
	ArrayList<MyPolygon> polygons = new ArrayList<MyPolygon>();
	
	public MyPolygons() {
		
	}
	
	public MyPolygons(MyPolygon polygon) {
		polygons.add(polygon);
	}
	
	public void addPolygon(MyPolygon polygon) {
		polygons.add(polygon);
	}
	
	public void printPolygons() {
		for (MyPolygon polygon: polygons) {
			polygon.printCoordinates();
		}
	}
}
