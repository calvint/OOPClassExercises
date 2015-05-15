package Workshop5;

import javafx.stage.*; 
import javafx.scene.control.*; 
import javafx.event.*; 

public class JavaFXDialogBoxRoot extends JavaFXDialogBoxChild { 

	TextField temperatureField;
	Label     temperatureLabel; 
	TextField   windSpeedField;
	Label       windSpeedLabel; 
	WeatherDataHub hub;
	
	JavaFXDialogBoxRoot(Stage stage) {
		super(stage);
		objectName = "JavaFXDialogBoxRoot";
		this.hub = new WeatherDataHub(75d, 5d);
	}

	// Override the init() method. 
	public void init() { 
		super.init(); 
	} 
	
	// Override the start() method. 
	public void start() { 

		// Temperature handling
		temperatureField = new TextField();
		temperatureField.setText("75");    // Default at 75 degrees
		temperatureField.setPrefColumnCount(8);  // Set the width of the text box
		temperatureField.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				try{
					double newTemperature = Double.parseDouble(temperatureField.getText());
					System.out.println(objectName + ": You changed temperature to: " + newTemperature);
					hub.setData( Double.parseDouble(temperatureField.getText()), Double.parseDouble(windSpeedField.getText()) );
				}
				catch (Exception e) {
					System.out.println("incorrect input... ");
				}
			}
		});
		temperatureLabel = new Label("Temperature");
		
		// Separator 1 
		Separator separator1 = new Separator(); 
		separator1.setPrefWidth(400);
		
		// Wind speed handling
		windSpeedField = new TextField();
		windSpeedField.setText("5");    // Default at 5 knots
		windSpeedField.setPrefColumnCount(8);  // Set the width of the text box
		windSpeedField.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				double newWindSpeed = Double.parseDouble(windSpeedField.getText());
				System.out.println(objectName + ": You changed wind speed to: " + newWindSpeed);
				hub.setData( Double.parseDouble(temperatureField.getText()), Double.parseDouble(windSpeedField.getText()) );
			}
		});
		windSpeedLabel = new Label("Wind Speed");
		
		// Separator 2 
		Separator separator2 = new Separator(); 
		separator2.setPrefSize(400, 20);
		
		// Add to scene
		rootNode.getChildren().addAll(temperatureField, temperatureLabel, separator1);
		rootNode.getChildren().addAll(  windSpeedField,   windSpeedLabel, separator2);
		
		// Run start from super class. 
		// This will add Listener to the dialogBox.
		super.start();

		// Separator 3 
		Separator separator3 = new Separator(); 
		separator3.setPrefWidth(400);
		
		WeatherDataHub ahub = this.hub;
		
		// Create a button and handle its action
		Button btnNewListener = new Button("New Listener");
		btnNewListener.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				System.out.println(objectName + ": You pressed button: New Listener.");
				JavaFXDialogBox newBox = new JavaFXDialogBoxChild(new Stage(), hub);
				newBox.init();
				newBox.initOwner(stage);
				newBox.start();
			}
		});
		
		rootNode.getChildren().addAll(       separator3);
		rootNode.getChildren().addAll(   btnNewListener);

		// Change the height
		stage.setHeight(180);
	} 

}
