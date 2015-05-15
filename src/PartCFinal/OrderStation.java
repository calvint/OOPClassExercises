package PartCFinal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OrderStation extends AbstractStation {
	public static OrderQueue orderQueue;

	protected Label beverageLabel;
	protected Label condimentLabel;
	protected Label costLabel;
	protected Label orderSummaryLabel;
	protected Beverage currentBeverage;
	private Order currentOrder;

	OrderStation() {
		this(new JavaFXStage(new Stage()));
	}

	OrderStation(JavaFXStage window) {
		super(window);
		window.setObjectName("OrderStation");
		window.init();
		window.setWidth(600);
		window.setHeight(400);

		if (orderQueue == null)
			orderQueue = new OrderQueue("order_log.txt");
	}

	void eventNewBeverageSelection(String beverage) {
		Beverage b = null;
		try {
			b = (Beverage) Class.forName("PartCFinal." + beverage).getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		currentBeverage = b;
		costLabel.setText("Order cost: $" + (b.cost() + currentOrder.getCost()));
		beverageLabel.setText("Beverage: " + beverage);
		condimentLabel.setText("");
		System.out.println(window.getObjectName() + ": You changed beverage to: " + beverage);
	}

	void eventAddCondiment(String condiment) {
		if (condiment.equals("2% Milk"))
			condiment = "TwoPercentMilk";
		if (currentBeverage == null)
			return;
		Beverage b = null;
		try {
			b = (CondimentDecorator) Class.forName("PartCFinal." + condiment).getConstructor(Beverage.class).newInstance(currentBeverage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		currentBeverage = b;
		costLabel.setText("Order cost: $" + (b.cost() + currentOrder.getCost()));
		condimentLabel.setText("Condiments: \n" + currentBeverage.getCondiments());
		System.out.println(window.getObjectName() + ": You added condiment: " + condiment);
	}

	void eventControl(String controlCommand) {
		if (controlCommand.equals("Cancel Beverage")) {
			currentBeverage = null;
			beverageLabel.setText("Select a Beverage");
			condimentLabel.setText("");
		}
		if (controlCommand.equals("Cancel Last Condiment")) {
			if (currentBeverage instanceof CondimentDecorator) {
				currentBeverage = ((CondimentDecorator) currentBeverage).beverage;
			}
			condimentLabel.setText("Condiments: \n" + currentBeverage.getCondiments());
		}
		if (controlCommand.equals("Cancel Order")) {
			currentOrder = orderQueue.newOrder();
			costLabel.setText("Order cost: $0.00");
		}
		if (controlCommand.equals("Add Beverage to Order")) {
			currentOrder.addBeverage(currentBeverage);
			eventControl("Cancel Beverage");
		}
		if (controlCommand.equals("Finish Order and Pay")) {
			orderQueue.queueOrder(currentOrder);
			currentOrder = orderQueue.newOrder();
		}
		costLabel.setText("Order cost: $" + currentOrder.getCost());
		orderSummaryLabel.setText("Order Summary\n" + currentOrder.getCostSummary());
		System.out.println(window.getObjectName() + ": You pressed control command: " + controlCommand);
	}

	void setCondimentButton(Button button, String text, int buttonCndmntPrefWidth) {
		button.setPrefWidth(buttonCndmntPrefWidth);
		button.setAlignment(Pos.BASELINE_LEFT);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventAddCondiment(text);
			}
		});
	}

	void setControlButton(Button button, String text, int buttonCndmntPrefWidth) {
		button.setPrefWidth(buttonCndmntPrefWidth);
		button.setAlignment(Pos.BASELINE_LEFT);
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventControl(text);
			}
		});
	}

	// Load up and start OrderStation window.
	@Override
	public void start() {
		currentOrder = orderQueue.newOrder();

		GridPane gridPaneAll = new GridPane();
		gridPaneAll.setHgap(10);
		gridPaneAll.setVgap(10);
		GridPane gridPaneInput = new GridPane();
		gridPaneInput.setHgap(10);
		gridPaneInput.setVgap(10);

		// ListBox of beverages
		GridPane gridPaneBeverages = new GridPane();
		ObservableList<String> stringListOfBeverageTypes = FXCollections.observableArrayList("HouseBlend", "DarkRoast", "Espresso", "Decaf", "Froffee");
		ListView<String> lvBeverageTypes = new ListView<String>(stringListOfBeverageTypes);
		lvBeverageTypes.setPrefSize(150, 120); // (prefWidth, prefHeight);
		MultipleSelectionModel<String> lvSelectionModel = lvBeverageTypes.getSelectionModel();
		lvSelectionModel.selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> changed, Number oldVal, Number newVal) {
				eventNewBeverageSelection(stringListOfBeverageTypes.get((int) newVal));
			}
		});
		beverageLabel = new Label("Select a Beverage");
		GridPane.setValignment(beverageLabel, VPos.TOP);
		// gridPaneBeverages.add( beverageLabel, 0, 0);
		gridPaneBeverages.add(lvBeverageTypes, 0, 1);
		gridPaneInput.add(gridPaneBeverages, 1, 0);
		gridPaneInput.add(beverageLabel, 1, 1);

		// Add condiment buttons
		int buttonCndmntPrefWidth = 90;
		GridPane gridPaneCondiments = new GridPane();
		// Buttons
		Button btnAddMilk = new Button("Add Milk");
		Button btnAddMocha = new Button("Add Mocha");
		Button btnAddSoy = new Button("Add Soy");
		Button btnAddWhip = new Button("Add Whip");
		Button btnAdd2PrcntMilk = new Button("Add 2% Milk");
		// Set behavior
		setCondimentButton(btnAddMilk, "Milk", buttonCndmntPrefWidth);
		setCondimentButton(btnAddMocha, "Mocha", buttonCndmntPrefWidth);
		setCondimentButton(btnAddSoy, "Soy", buttonCndmntPrefWidth);
		setCondimentButton(btnAddWhip, "Whip", buttonCndmntPrefWidth);
		setCondimentButton(btnAdd2PrcntMilk, "2% Milk", buttonCndmntPrefWidth);
		// Add to part of scene
		gridPaneCondiments.add(btnAddMilk, 0, 0);
		gridPaneCondiments.add(btnAddMocha, 0, 1);
		gridPaneCondiments.add(btnAddSoy, 0, 2);
		gridPaneCondiments.add(btnAddWhip, 0, 3);
		gridPaneCondiments.add(btnAdd2PrcntMilk, 0, 4);
		// Add to gridPaneInput
		condimentLabel = new Label("Add a Condiment");
		condimentLabel.setPrefWidth(100);
		gridPaneInput.add(gridPaneCondiments, 2, 0);
		gridPaneInput.add(condimentLabel, 2, 1);

		// Add order control buttons
		int buttonCntrlPrefWidth = 140;
		GridPane gridPaneControls = new GridPane();
		// Buttons
		Button btnCnclOrder = new Button("Cancel Order");
		Button btnCnclBeverage = new Button("Cancel Beverage");
		Button btnCnclLstCndmnt = new Button("Cancel Last Condiment");
		Button btnAddBeverage = new Button("Add Beverage to Order");
		Button btnFinishOrder = new Button("Finish Order and Pay");
		// Set button action events to the event methods of this class
		setControlButton(btnCnclOrder, "Cancel Order", buttonCntrlPrefWidth);
		setControlButton(btnCnclBeverage, "Cancel Beverage", buttonCntrlPrefWidth);
		setControlButton(btnCnclLstCndmnt, "Cancel Last Condiment", buttonCntrlPrefWidth);
		setControlButton(btnAddBeverage, "Add Beverage to Order", buttonCntrlPrefWidth);
		setControlButton(btnFinishOrder, "Finish Order and Pay", buttonCntrlPrefWidth);
		// Add to part of scene
		gridPaneControls.add(btnCnclOrder, 0, 0);
		gridPaneControls.add(btnCnclBeverage, 0, 1);
		gridPaneControls.add(btnCnclLstCndmnt, 0, 2);
		gridPaneControls.add(btnAddBeverage, 0, 3);
		gridPaneControls.add(btnFinishOrder, 0, 4);
		// Add to gridPaneInput
		costLabel = new Label("Order cost: $0.00");
		GridPane.setValignment(costLabel, VPos.TOP);
		gridPaneInput.add(gridPaneControls, 3, 0);
		gridPaneInput.add(costLabel, 3, 1);

		//
		orderSummaryLabel = new Label("Order Summary Here");
		GridPane.setValignment(orderSummaryLabel, VPos.TOP);
		GridPane.setHalignment(orderSummaryLabel, HPos.LEFT);
		gridPaneAll.add(gridPaneInput, 0, 1);
		gridPaneAll.add(orderSummaryLabel, 1, 1);

		window.addNodesAsChildrenToRoot(gridPaneAll);

		// Put the final touches and display the window.
		window.start();
	}

	@Override
	void refreshDisplay() {
	} // There is nothing to refresh for this class
}
