package Midterm;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CashierStation extends AbstractStation {
	
	private LogWriter log;
	protected ArrayList<MultiOrderDisplayStation> stationCollection = new ArrayList<MultiOrderDisplayStation>();
	protected ArrayList<Order> orderQueue = new ArrayList<Order>();
	protected Order       orderDisplayed;
	protected Label   textLabelDisplayed;

	CashierStation() {
		this(new JavaFXStage(new Stage()));
	}

	CashierStation(JavaFXStage window, AbstractStation ownerStation, LogWriter log) {
		this( window );
		window.initOwner(ownerStation.getBaseWindow());
		this.log = log;
	}

	CashierStation(JavaFXStage window) {
		super( window );
		window.setObjectName("CashierStation");
		window.init();
		window.setWidth( 140);
		window.setHeight(500);
	}
	
	public void initStations(ArrayList<MultiOrderDisplayStation> stationCollection) {
		this.stationCollection = stationCollection;
	}
	
	public void addToOrderQueue(Order order) {
		orderQueue.add(order);
		refreshDisplay();
	}
	
	void eventOrderPoured(String command) {
		if (orderQueue.size() != 0) {
			if (orderDisplayed != null) {
				// Logic for Order update to filled here
				if (command == "Paid") {
					for (MultiOrderDisplayStation station : stationCollection) {
						station.isPaidFor(orderDisplayed);
					}
				}
				if (command == "Canceled") {
					for (MultiOrderDisplayStation station : stationCollection) {
						station.hasBeenRemoved(orderDisplayed);
					}
				}
				orderQueue.remove(0);
				log.writeToLog(window.getObjectName() + ": Order: " + Integer.toString(orderDisplayed.getOrderNumber()) + " was " + command); 
				
				// Display next unpaid order
				refreshDisplay();
			}
		}
	}

	@Override
	void start() {
		// Order is displayed with a "Processed" button at top and order description at the bottom.
		Button btnPayed    = new Button("Paid"    );
		Button btnCanceled = new Button("Canceled");
		// Set button action events to the event methods of this class
		setControlButton(btnPayed   , "Paid"    );
		setControlButton(btnCanceled, "Canceled");
		
		textLabelDisplayed = new Label("");
		textLabelDisplayed.setPrefWidth(120);

		GridPane gridPaneAll = new GridPane();
		gridPaneAll.setVgap(10);
		GridPane gridPaneOrder = new GridPane();
		gridPaneOrder.setHgap(10);
		gridPaneOrder.add( btnPayed   , 0, 0);
		gridPaneOrder.add( btnCanceled, 1, 0);
		gridPaneAll.add(      gridPaneOrder, 0, 0);
		gridPaneAll.add( textLabelDisplayed, 0, 1);

		window.addNodesAsChildrenToRoot(gridPaneAll);
		
		// Put the final touches and display the window.
		window.start();
		
		refreshDisplay();
	}

	void setControlButton(Button button, String command) {
		final String cmd = command;
		button.setOnAction(new EventHandler<ActionEvent>() {			public void handle(ActionEvent ae) {				eventOrderPoured(cmd);			}		});	}
	@Override
	void refreshDisplay() {
		
		// If the number of orders that have not been payed for > 0 
		if (orderQueue.size() > 0 ) {
			orderDisplayed = orderQueue.get(0);
			String orderString =  buildDisplayLabelString(orderDisplayed);
	
			textLabelDisplayed.setText(orderString);
		}
		// Else == 0
		else {
			textLabelDisplayed.setText("");
		}
	}
	
	String buildDisplayLabelString(Order order) {
		return "Order \n" + Integer.toString(order.getOrderNumber()) + "\n" + order.getDescription() + "\n Cost: " + Double.toString(order.getCost());
	}
}
