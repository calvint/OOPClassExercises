package workshop9;

import java.util.ArrayList;

import javafx.application.*; 
import javafx.stage.*; 

public class JavaFXApplication extends Application { 
	public static void main(String[] args) {
		// Start the JavaFX application by calling launch(). 
		launch(args);   
	} 
	
	// Override the init() method. 
	public void init() { 
	} 
	
	// Override the start() method. 
	public void start(Stage myStage) { 
		ShapeAdapter shapeAdapter = new ShapeAdapter(new JavaFXStage(new Stage()));
		shapeAdapter.start();
	} 

	// Override the stop() method. 
	public void stop() { 
	} 
}