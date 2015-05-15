package workshop2;

//A JavaFX application skeleton. 

import java.util.ArrayList;

import javafx.application.*; 
import javafx.event.EventHandler;
import javafx.scene.*; 
import javafx.stage.*; 
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class JavaFXSkel extends Application { 
	private MyPolygons polygons;
	private MyPolygon currentPolygon;
	
	public static void main(String[] args) { 
		
		 System.out.println("Launching JavaFX application."); 
		
		 // Start the JavaFX application by calling launch(). 
		 launch(args);   
	} 
	
	// Override the init() method. 
	public void init() { 
		 System.out.println("Inside the init() method."); 
		 polygons = new MyPolygons();
	} 
	
	// Override the start() method. 
	public void start(Stage myStage) { 
	
	 System.out.println("Inside the start() method."); 
	
	 // Give the stage a title. 
	 myStage.setTitle("JavaFX Skeleton."); 
	
	 // Create a root node. In this case, a flow layout 
	 // is used, but several alternatives exist. 
	 FlowPane rootNode = new FlowPane(); 
	
	 // Create a scene. 
	 Scene myScene = new Scene(rootNode, 300, 200); 
	
	 // Set the scene on the stage. 
	 myStage.setScene(myScene); 
	
	 myStage.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	if (e.getButton() == MouseButton.PRIMARY){
		        		System.out.println("Primary (usually left) button: X: " + e.getX() + "; Y: " + e.getY());
		        		//if there are more than two left clicks add a polygon to the collection and set current polygon to null
			        	if (currentPolygon != null) {
		        			if ( currentPolygon.size() >2) {
			        			polygons.addPolygon(currentPolygon);
			        			currentPolygon = null;
			        		}
		        			else {
		        				System.out.println("not enough points in polygon...");
		        			}
			        	}
		        		//else print error message and do nothing
		        		else {
		        			System.out.println("Make a polygon...");
		        		}
		        	}
		        	else {
		        		System.out.println("Non-primary (usually right) button: X: " + e.getX() + "; Y: " + e.getY());
		        		//if there is not already a polygon, make one.
		        		if (currentPolygon == null) {
		        			currentPolygon = new MyPolygon(new ArrayList<xyPair>());
		        		}
		        		//add a new coordinate to the current polygon
		        		currentPolygon.addCoordiante(e.getX(), e.getY());
		        	}
		        }
		});
	
	 
	 // Show the stage and its scene. 
	 myStage.show(); 
	} 
	
	// Override the stop() method. 
	public void stop() { 
		System.out.println("Inside the stop() method."); 
		polygons.printPolygons();
	} 
}