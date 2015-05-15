package workshop3;

//A JavaFX application skeleton. 

import javafx.application.*; 
import javafx.scene.*; 
import javafx.stage.*; 
import javafx.scene.layout.*; 
import javafx.scene.control.*; 
import javafx.event.*; 
import javafx.geometry.*; 
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class JavaFXSkel extends Application { 
	private MyColoredPaths paths;
	Label response; 

	public static void main(String[] args) { 
		System.out.println("Launching JavaFX application."); 

		// Start the JavaFX application by calling launch(). 
		launch(args);   
	} 

	// Override the init() method. 
	public void init() { 
		System.out.println("Inside the init() method."); 
		paths = new MyColoredPaths();
	} 
	
	// Override the start() method. 
	public void start(Stage myStage) { 

		System.out.println("Inside the start() method."); 

		// Give the stage a title. 
		myStage.setTitle("JavaFX Skeleton."); 

		// Use a FlowPane for the root node. In this case, 
		// vertical and horizontal gaps of 10. 
		FlowPane rootNode = new FlowPane();//10, 10); 

		// Center the controls in the scene. 
		rootNode.setAlignment(Pos.BOTTOM_RIGHT); 

		// Create a scene. 
		Scene myScene = new Scene(rootNode, 300, 100); 

		// Set the scene on the stage. 
		myStage.setScene(myScene); 

		// Create a button and handle its action
		Button btn = new Button("Last");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				System.out.println("You pressed last.");
				paths.newPath();
			}
		});
		
		rootNode.getChildren().addAll(btn);

		myStage.addEventHandler(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					System.out.println("Primary (usually left) button: X: " + e.getX() + "; Y: " + e.getY());
					paths.getCurrentPath().insertNode(new RedNode(e.getX(), e.getY()));
				}
				else {
					System.out.println("Non-primary (usually right) button: X: " + e.getX() + "; Y: " + e.getY());
					paths.getCurrentPath().insertNode(new BlackNode(e.getX(), e.getY()));
				}
			}
		});

		// Show the stage and its scene. 
		myStage.show(); 
	} 

	// Override the stop() method. 
	public void stop() { 
		System.out.println("Inside the stop() method."); 
		paths.printPaths();
	} 
}
