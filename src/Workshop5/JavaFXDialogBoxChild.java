package Workshop5;

import javafx.stage.*; 
import javafx.scene.control.*; 
import javafx.event.*; 

public class JavaFXDialogBoxChild extends JavaFXDialogBox { 

	public CheckBox listenerChkBx;
	Label    listenerLabel; 
	WeatherObserver observer;

	JavaFXDialogBoxChild(Stage stage) {
		super(stage);
		objectName = "JavaFXDialogBoxChild";
	}
	
	JavaFXDialogBoxChild(Stage stage, WeatherDataHub hub) {
		super(stage);
		this.initObserver(hub);
		objectName = "JavaFXDialogBoxChild";
	}
	
	//pass root data
	public void initObserver(WeatherDataHub hub) {
		observer = new WeatherObserver(hub, this);
	}
	
	// Override the init() method. 
	public void init() { 
		super.init(); 
	} 
	
	// Override the start() method. 
	public void start() { 

		// Listener handling
		listenerChkBx = new CheckBox("Listener: not listening");
		listenerChkBx.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				if(listenerChkBx.isSelected()) {
					listenerChkBx.setText("Listener should now be listening.");
					System.out.println(objectName + ": Listener should now be listening.");
				}
				else {
					listenerChkBx.setText("Listener should now be off.");
					System.out.println(objectName + ": Listener should now be off.");
				}
			}
		});
		listenerLabel = new Label("Temperature: 0; Wind Speed: 0");

		
		// Add to scene
		rootNode.getChildren().addAll(listenerChkBx, listenerLabel);
		
		// Change the height to something smaller
		stage.setHeight(80);

		// Run start from super class. 
		super.start();
	} 
	
	public void updateListnerLabel(double temperature, double windSpeed) {
		listenerLabel.setText("Temperature: "+temperature+"; Wind Speed: "+windSpeed);
	}
}

