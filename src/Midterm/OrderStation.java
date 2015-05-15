package Midterm;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.sun.jmx.remote.util.OrderClassLoaders;

import javafx.stage.*; 
import javafx.scene.control.*; 
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*; 
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class OrderStation extends AbstractStation{ 
	
	
	private LogWriter log;
	private CashierStation cs = null;
	private PourBeverageStation bs = null;
	private Drink currentDrink = null;
	private Order currentOrder = new Order(0);
	private int orderNumber = 0;
	protected Label           beverageLabel; 
	protected Label          condimentLabel; 
	protected Label               costLabel; 
	protected Label       orderSummaryLabel; 
	
	OrderStation(LogWriter log) {
		this(new JavaFXStage(new Stage()));
		this.log = log;
	}

	OrderStation(JavaFXStage window) {
		super( window );
		window.setObjectName("OrderStation");
		window.init();
		window.setWidth( 600);
		window.setHeight(400);
	}
	
	private Boolean isOfClassCondiment(Object object) {
		try {
			Condiment condiment = (Condiment) object;
			return true;
		} catch(ClassCastException e) {
			return false;
		}
	}
	
	Condiment findUppermostCondimentChildOfCurrentDrink() {
		Condiment child = null;
		Drink parent = currentDrink;
		if (currentDrink == null) {
			return null;
		}
		if (!( isOfClassCondiment(parent))) {
			return null;
		} else {
			while (isOfClassCondiment(parent)) {
				child = (Condiment)( (Object) parent);
				parent = child.drink;
			}
			return child;
		}
	}
	
	void eventNewBeverageSelection(String beverage) {
		try {
			Drink newDrink = (Drink) Class.forName(beverage).newInstance();
			Condiment child = findUppermostCondimentChildOfCurrentDrink();
			if (child == null) {
				currentDrink = newDrink;
			} else {
				child.drink = newDrink;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//costLabel     .setText("Order cost: $ FIX ME!!!");
		beverageLabel .setText("Beverage: " + beverage);
		//condimentLabel.setText(condimentLabel.getText() + "\nDON'T FORGET \nTO RESET");
		log.writeToLog(window.getObjectName() + ": You changed beverage to: " + beverage);
	}

	void eventAddCondiment(String condiment) {
		try {
			Class<?> clazz = Class.forName(condiment);
			Constructor<?> constructor = clazz.getConstructor(Drink.class);
			Condiment midMan = ((Condiment) constructor.newInstance(currentDrink));
			currentDrink =  midMan;
		} catch (Exception e) {
			e.printStackTrace();
		}
		condimentLabel.setText(currentDrink.getDescription());
		log.writeToLog(window.getObjectName() + ": You added condiment: " + condiment);
	}
	
	void eventControl(String controlCommand) {
		if (controlCommand == "Cancel Order") {
			currentOrder = new Order(orderNumber);
			costLabel        .setText("Order cost: $ 0.00");
			orderSummaryLabel.setText("");
			eventControl("Cancel Beverage");
		}
		if (controlCommand == "Cancel Beverage") {
			currentDrink = null;
			beverageLabel .setText("Beverage: ");
			condimentLabel.setText("");
		}
		if (controlCommand == "Cancel Last Condiment") {
			if (currentDrink != null && isOfClassCondiment(currentDrink)) {
				currentDrink = ((Condiment) currentDrink).drink;
				condimentLabel.setText(currentDrink.getDescription());
			}
		}
		if (controlCommand == "Add Beverage to Order") {
			if (currentDrink != null) {
				currentOrder.addDrink(currentDrink);
				Double cost = currentOrder.getCost();
				costLabel        .setText("Order cost: $ " + Double.toString(cost));
				orderSummaryLabel.setText(currentOrder.getDescription());
				eventControl("Cancel Beverage");
			}
		}
		if (controlCommand == "Finish Order and Pay" ) {
			//add order to cachiers station queue
			cs.addToOrderQueue(currentOrder);
			
			//add order to pour station queue
			bs.addOrderToQueue(currentOrder);
			//increment order count
			orderNumber ++;
			//clear current drink and make new currentOrder
			eventControl("Cancel Order");
		}
		
		log.writeToLog(window.getObjectName() + ": You pressed control command: " + controlCommand);
	}

	void setCondimentButton(Button button, String text, int buttonCndmntPrefWidth) {
		final String txt = text;
		button.setPrefWidth(buttonCndmntPrefWidth);
		button.setAlignment(Pos.BASELINE_LEFT);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventAddCondiment(txt);
			}
		});
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
	
	public void initStations(CashierStation cs, PourBeverageStation bs) {
		this.cs = cs;
		this.bs = bs;
	}
	
	// Load up and start OrderStation window. 
	@Override
	public void start() { 
		GridPane gridPaneAll   = new GridPane();
		gridPaneAll.setHgap(10);
		gridPaneAll.setVgap(10);
		GridPane gridPaneInput = new GridPane();
		gridPaneInput.setHgap(10);
		gridPaneInput.setVgap(10);

		// ListBox of beverages
		GridPane gridPaneBeverages = new GridPane();
		final ObservableList<String> stringListOfBeverageTypes = FXCollections.observableArrayList("HouseBlend", "DarkRoast", "Espresso", "Decaf", "Froffee"); 
		ListView<String> lvBeverageTypes = new ListView<String>(stringListOfBeverageTypes);
		lvBeverageTypes.setPrefSize(150,120); //(prefWidth, prefHeight);
		MultipleSelectionModel<String> lvSelectionModel = lvBeverageTypes.getSelectionModel();
		lvSelectionModel.selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> changed, Number oldVal, Number newVal) {
				eventNewBeverageSelection(stringListOfBeverageTypes.get((int)newVal.intValue()));
			}
		});
		beverageLabel = new Label("Select a Beverage");
		GridPane.setValignment(beverageLabel, VPos.TOP);
		//gridPaneBeverages.add(  beverageLabel, 0, 0);
		gridPaneBeverages.add(lvBeverageTypes, 0, 1);
		gridPaneInput.add(gridPaneBeverages, 1, 0);
		gridPaneInput.add(    beverageLabel, 1, 1);
		
		// Add condiment buttons
		int buttonCndmntPrefWidth = 90;
		GridPane gridPaneCondiments = new GridPane();
		// Buttons
		Button btnAddMilk       = new Button("Add Milk"    );
		Button btnAddMocha      = new Button("Add Mocha"   );
		Button btnAddSoy        = new Button("Add Soy"     );
		Button btnAddWhip       = new Button("Add Whip"    );
		Button btnAdd2PrcntMilk = new Button("Add TwoPercentMilk"  );
		// Set behavior
		setCondimentButton( btnAddMilk      ,   "Milk", buttonCndmntPrefWidth);
		setCondimentButton( btnAddMocha     ,  "Mocha", buttonCndmntPrefWidth);
		setCondimentButton( btnAddSoy       ,    "Soy", buttonCndmntPrefWidth);
		setCondimentButton( btnAddWhip      ,   "Whip", buttonCndmntPrefWidth);
		setCondimentButton( btnAdd2PrcntMilk, "TwoPercentMilk", buttonCndmntPrefWidth);
		// Add to part of scene
		gridPaneCondiments.add(btnAddMilk      , 0, 0);
		gridPaneCondiments.add(btnAddMocha     , 0, 1);
		gridPaneCondiments.add(btnAddSoy       , 0, 2);
		gridPaneCondiments.add(btnAddWhip      , 0, 3);
		gridPaneCondiments.add(btnAdd2PrcntMilk, 0, 4);
		// Add to gridPaneInput
		condimentLabel = new Label("Add a Condiment");
		condimentLabel.setPrefWidth(100);
		gridPaneInput.add(gridPaneCondiments, 2, 0);
		gridPaneInput.add(    condimentLabel, 2, 1);

		// Add order control buttons
		int buttonCntrlPrefWidth = 140;
		GridPane gridPaneControls = new GridPane();
		// Buttons
		Button btnCnclOrder     = new Button("Cancel Order"         );
		Button btnCnclBeverage  = new Button("Cancel Beverage"      );
		Button btnCnclLstCndmnt = new Button("Cancel Last Condiment");
		Button btnAddBeverage   = new Button("Add Beverage to Order");
		Button btnFinishOrder   = new Button("Finish Order and Pay" );
		// Set button action events to the event methods of this class
		setControlButton( btnCnclOrder     , "Cancel Order"         , buttonCntrlPrefWidth);
		setControlButton( btnCnclBeverage  , "Cancel Beverage"      , buttonCntrlPrefWidth);
		setControlButton( btnCnclLstCndmnt , "Cancel Last Condiment", buttonCntrlPrefWidth);
		setControlButton( btnAddBeverage   , "Add Beverage to Order", buttonCntrlPrefWidth);
		setControlButton( btnFinishOrder   , "Finish Order and Pay" , buttonCntrlPrefWidth);
		// Add to part of scene
		gridPaneControls.add(btnCnclOrder     , 0, 0);
		gridPaneControls.add(btnCnclBeverage  , 0, 1);
		gridPaneControls.add(btnCnclLstCndmnt , 0, 2);
		gridPaneControls.add(btnAddBeverage   , 0, 3);
		gridPaneControls.add(btnFinishOrder   , 0, 4);
		// Add to gridPaneInput
		costLabel = new Label("Order cost: $ 0.00");
		GridPane.setValignment(costLabel, VPos.TOP);
		gridPaneInput.add(  gridPaneControls, 3, 0);
		gridPaneInput.add(         costLabel, 3, 1);

		// 
		orderSummaryLabel = new Label("Order Summary Here"); 
		GridPane.setValignment(orderSummaryLabel, VPos.TOP );
		GridPane.setHalignment(orderSummaryLabel, HPos.LEFT);
		gridPaneAll.add(    gridPaneInput, 0, 1);
		gridPaneAll.add(orderSummaryLabel, 1, 1);

		window.addNodesAsChildrenToRoot(gridPaneAll);
		
		// Put the final touches and display the window.
		window.start();
	} 
	
	@Override
	void refreshDisplay() {} // There is nothing to refresh for this class
}
