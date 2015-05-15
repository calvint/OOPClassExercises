package Midterm;
import javafx.event.EventHandler;
import javafx.scene.*; 
import javafx.stage.*; 
import javafx.scene.layout.*; 

public class JavaFXStage { 

	protected       String   objectName;
	final protected Stage    stage   ;
	final protected FlowPane rootNode; // Use a FlowPane for the root node.
	
	JavaFXStage(Stage stage) {
		this.stage = stage;

		// Use a FlowPane for the root node. In this case, 
		// vertical and horizontal gaps of 10. 
		rootNode = new FlowPane();//10, 10); 
	}

	JavaFXStage(Stage stage, Stage ownerStage) {
		this(stage);
		initOwner(ownerStage);
	}

	// Set window owner (So that when owner window closes, this one also closes).
	public void initOwner(JavaFXStage owner) {
		initOwner(owner.stage);
	}
	private void initOwner(Window owner) {
		stage.initOwner(owner);
	}
	void setWidth(double value) {
		stage.setWidth( value);
	}
	void setHeight(double value) {
		stage.setHeight(value);
	}
	
	void setObjectName(String name) {
		objectName = name;
	}
	String getObjectName() {
		return objectName;
	}
	
	boolean addNodesAsChildrenToRoot(Node...elements) {
		return rootNode.getChildren().addAll(elements);
	}
	
	// init() method prepares the dialog box. 
	public void init() { 
		System.out.println(objectName + ": Inside the init() method."); 

		// Give the stage a title. 
		stage.setTitle(objectName + ""); 

		// Center the controls in the scene. 
		//rootNode.setAlignment(Pos.BOTTOM_RIGHT); 

		// Create a scene. 
		Scene myScene = new Scene(rootNode, 100, 100); 

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

	// Override the stop() method. 
	public void stop() { 
		System.out.println(objectName + ": Inside the stop() method."); 
	} 
}

