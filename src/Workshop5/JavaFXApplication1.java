package Workshop5;


import javafx.application.*; 
//import javafx.scene.*; 
import javafx.stage.*; 
//import javafx.scene.layout.*; 
//import javafx.scene.control.*; 
//import javafx.event.*; 
//import javafx.geometry.*; 

public class JavaFXApplication1 extends Application { 

	protected String objectName = "JavaFXApplication";
	protected JavaFXDialogBox rootDialog;

	public static void main(String[] args) { 
		System.out.println("Launching JavaFX application."); 

		// Start the JavaFX application by calling launch(). 
		launch(args);   
	} 
	
	// Override the init() method. 
	public void init() { 
		System.out.println(objectName + ": Inside the init() method."); 
	} 
	
	// Override the start() method. 
	public void start(Stage myStage) { 

		System.out.println(objectName + ": Inside the start() method."); 

		rootDialog = new JavaFXDialogBoxRoot(myStage);

		rootDialog.init(); 

		rootDialog.start();
	} 

	// Override the stop() method. 
	public void stop() { 
		System.out.println(objectName + ": Inside the stop() method."); 
	} 
}

