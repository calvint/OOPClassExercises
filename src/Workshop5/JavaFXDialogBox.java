package Workshop5;

import javafx.event.EventHandler;
import javafx.scene.*; 
import javafx.stage.*; 
import javafx.scene.layout.*; 

abstract public class JavaFXDialogBox { 

	protected String   objectName = "JavaFXDialogBox";
	protected Stage    stage   ;
	protected FlowPane rootNode; // Use a FlowPane for the root node.
	
	JavaFXDialogBox(Stage stage) {
		this.stage = stage;
	}

	// Set window owner (So that when root closes, this one also closes).
	void initOwner(Window owner) {
		stage.initOwner(owner);
	}
	
	// init() method prepares the dialog box. 
	public void init() { 
		System.out.println(objectName + ": Inside the init() method."); 

		// Give the stage a title. 
		stage.setTitle(objectName + ""); 

		// Use a FlowPane for the root node. In this case, 
		// vertical and horizontal gaps of 10. 
		rootNode = new FlowPane();//10, 10); 

		// Center the controls in the scene. 
		//rootNode.setAlignment(Pos.BOTTOM_RIGHT); 

		// Create a scene. 
		Scene myScene = new Scene(rootNode, 200, 120); 

		// Define behavior on close of the box.
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    public void handle(WindowEvent event) {
				stop();
				stage.close();
		    }
		});


		// Set the scene on the stage. 
		stage.setScene(myScene); 
	} 
	
	// start() method launches the dialog box. 
	public void start() { 
		System.out.println(objectName + ": Inside the start() method."); 

		stage.show(); 
	} 

	abstract void updateListnerLabel(double temperature, double windSpeed);

	// Override the stop() method. 
	public void stop() { 
		System.out.println(objectName + ": Inside the stop() method."); 
	} 
}

