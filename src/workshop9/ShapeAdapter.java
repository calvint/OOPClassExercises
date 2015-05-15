package workshop9;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.sun.jmx.remote.util.OrderClassLoaders;

import javafx.stage.*; 
import javafx.scene.control.*; 
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*; 
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class ShapeAdapter { 
	
	private int currentPosition = 0;
	private double currentAngle = 0.0;
	private GridPane inputGP;
	private Shape currentShape;
	private Circle myCircle;
	private Polygon myOctogon;
	private Polygon mySquare;
	private int orderNumber = 0;
	protected Label     shapeSelectionLabel; 
	protected Label     actionSelectionLabel;
	
	
	JavaFXStage  window; 

	

	protected JavaFXStage getBaseWindow() {
		return window;
	}

	ShapeAdapter(JavaFXStage window) {
		this.window = window;
		window.setObjectName("AbstractStation");
		window.setObjectName("OrderStation");
		window.init();
		window.setWidth( 600);
		window.setHeight(400);
	}
	
	void eventControl(String controlCommand) {
		if (controlCommand == "Cancel Order") {
			System.out.println("line 63 shape adapter");
		}
		if (controlCommand == "Cancel Beverage") {
			System.out.println("line 66 shape adapter");
		}
		if (controlCommand == "Cancel Last Condiment") {
			System.out.println("line 69 shape adapter");
		}
		if (controlCommand == "Add Beverage to Order") {
			System.out.println("line 73 shape adapter");
		}
		if (controlCommand == "Finish Order and Pay" ) {
			System.out.println("line 75 shape adapter");
		}
	}
	
	void setControlButton(Button button, String text, int buttonCndmntPrefWidth) {
		final String txt = text;
		button.setPrefWidth(buttonCndmntPrefWidth);
		button.setAlignment(Pos.BASELINE_LEFT);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventControl(txt);
			}
		});
	}
	
	
	
	// Load up and start window. 
	public void start() { 
		//initialize shapes
		myCircle = new Circle(50, Color.CHARTREUSE);
		mySquare = new Polygon();
	    mySquare.getPoints().addAll(new Double[]{
	        0.0, 0.0,
	        100.0, 0.0,
	        100.0, 100.0,
	        0.0, 100.0 });
	    myOctogon = new Polygon();
	    myOctogon.getPoints().addAll(new Double[]{
	        0.0, 0.0,
	        -20.0, 50.0,
	        0.0, 100.0,
	        50.0, 120.0,
	        100.0, 100.0,
	        120.0, 50.0,
	        100.0, 0.0,
	        50.0, -20.0});
		currentShape = myCircle;
		//initiate control shape behavior
		currentShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent mouseEvent) {
				eventMakeShapeDoSomething();
			}
		});
		
		GridPane gridPaneAll   = new GridPane();
		gridPaneAll.setHgap(10);
		gridPaneAll.setVgap(10);
		GridPane gridPaneInput = new GridPane();
		gridPaneInput.setHgap(10);
		gridPaneInput.setVgap(10);
		

		// ListBox of shapes
		GridPane gridPaneShapes = new GridPane();
		final ObservableList<String> stringListOfShapeTypes = FXCollections.observableArrayList("Circle", "Square", "Octagon"); 
		ListView<String> lvShapeTypes = new ListView<String>(stringListOfShapeTypes);
		lvShapeTypes.setPrefSize(150,120); //(prefWidth, prefHeight);
		MultipleSelectionModel<String> lvSelectionModel = lvShapeTypes.getSelectionModel();
		lvSelectionModel.selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> changed, Number oldVal, Number newVal) {
				eventNewShapeSelection(stringListOfShapeTypes.get((int)newVal.intValue()));
			}
		});
		shapeSelectionLabel = new Label("Select a Shape\nCurrent Shape:\nCircle");
		GridPane.setValignment(shapeSelectionLabel, VPos.TOP);
		gridPaneShapes.add(lvShapeTypes, 0, 1);
		gridPaneInput.add(gridPaneShapes, 1, 0);
		gridPaneInput.add(    shapeSelectionLabel, 1, 1);
		
		// ListBox of actions
		GridPane gridPaneActions = new GridPane();
		final ObservableList<String> stringListOfActionTypes = FXCollections.observableArrayList("Rotate 10 Degrees", "Rotate 25 Degrees", "Shake shake shake"); 
		ListView<String> lvActionTypes = new ListView<String>(stringListOfActionTypes);
		lvActionTypes.setPrefSize(150,120); //(prefWidth, prefHeight);
		MultipleSelectionModel<String> lvSelectionModelActions = lvActionTypes.getSelectionModel();
		lvSelectionModelActions.selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> changed, Number oldVal, Number newVal) {
				eventActionSelection(stringListOfActionTypes.get((int)newVal.intValue()));
			}
		});
		actionSelectionLabel = new Label("Select an Action\nCurrent Action:\nNone");
		GridPane.setValignment(actionSelectionLabel, VPos.TOP);
		gridPaneActions.add(lvActionTypes, 0, 1);
		gridPaneInput.add(gridPaneActions, 2, 0);
		gridPaneInput.add(    actionSelectionLabel, 2, 1);
		

		//Add shape
		GridPane shapeGridPane = new GridPane();
		inputGP = shapeGridPane;
		shapeGridPane.add(currentShape, 0, 0);
		gridPaneInput.add(  shapeGridPane, 3, 0);
		gridPaneAll.add(    gridPaneInput, 0, 1);

		window.addNodesAsChildrenToRoot(gridPaneAll);
		
		// Put the final touches and display the window.
		window.start();
	} 
	
	protected void eventActionSelection(String string) {
		actionSelectionLabel.setText(string);
		
	}

	protected void eventNewShapeSelection(String string) {
		if (string == "Circle") {
			currentShape = myCircle;
			shapeSelectionLabel.setText("Select a Shape\nCurrent Shape:\nCircle");
		} else if (string == "Square") {
			currentShape = mySquare;
			shapeSelectionLabel.setText("Select a Shape\nCurrent Shape:\nSquare");
		}else if (string == "Octagon") {
			currentShape = myOctogon;
			shapeSelectionLabel.setText("Select a Shape\nCurrent Shape:\nOctagon");
		}
		inputGP.getChildren().set(0, currentShape);
		currentShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(final MouseEvent mouseEvent) {
				eventMakeShapeDoSomething();
			}
		});
	}

	protected void eventMakeShapeDoSomething() {
		if (this.actionSelectionLabel.getText() == "Shake shake shake") {
			System.out.println("it should be shaking..");
			currentPosition += 1;
			if (currentPosition % 2 == 1) {
				currentShape.setTranslateX(10.0);
			} else {
				currentShape.setTranslateX(0.0);
			}
		} else if (this.actionSelectionLabel.getText() == "Rotate 10 Degrees") {
			System.out.println("it should be rotating 10 degrees..");
			currentAngle += 10.0;
			currentShape.setRotate(currentAngle);
		} else if (this.actionSelectionLabel.getText() == "Rotate 25 Degrees") {
			System.out.println("it should be rotating 25 degrees..");
			currentAngle += 25.0;
			currentShape.setRotate(currentAngle);
		}
		
	}

	void refreshDisplay() {}
}
