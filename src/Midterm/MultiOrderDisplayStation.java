package Midterm;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


abstract public class MultiOrderDisplayStation extends AbstractStation{
	
	private LogWriter log = new LogWriter("logfile.txt");
	protected ArrayList<Order> orderQueue = new ArrayList<Order>();
	protected Order      ordersDisplayed[];
	protected Label  textLabelsDisplayed[];
	protected int ordersDisplayedCount = 4;
	protected int    prefColumnWidth = 100;

	MultiOrderDisplayStation(JavaFXStage window) {
		super( window );
		window.setObjectName("MultiOrderDisplayStation");
		//window.init();
		
		// set the displayed orders
		ordersDisplayed     = new Order[ordersDisplayedCount];
		textLabelsDisplayed = new Label[ordersDisplayedCount];
	}
	
	void addOrderToQueue(Order order) {
		orderQueue.add(order);
		refreshDisplay();
	}
	
	void eventOrderProcessed(int iOrderDisplayed) {
		Order order = ordersDisplayed[iOrderDisplayed];

		log.writeToLog(window.getObjectName() + ": You processed beverages for order: " + "ORDER REFERENCE NUMBER HERE" + " based on button #" + iOrderDisplayed + " pressed."); 
		
		// Logic for Order update to filled here
		orderQueue.remove(iOrderDisplayed);
		// Redisplay unprocessed orders (without this order)
		refreshDisplay();
	}
	
	abstract void hasBeenRemoved(Order order);
	abstract void isPaidFor(Order order);
	abstract boolean isDisplayOrder(         Order order);
	abstract String  buildDisplayLabelString(Order order);
	
	@Override
	void start() { // set up the display
		GridPane gridPaneAll   = new GridPane();
		gridPaneAll.setHgap(10);

		// GridPane used to assign places for orders horizontally
		// Each order is displayed with a "Processed" button at top and order description at the bottom.
		for ( int iUnprocessedOrderPosition = 0; iUnprocessedOrderPosition < ordersDisplayedCount; iUnprocessedOrderPosition++ ) {
			Button btnProcessedAllBeverages = new Button("Processed");
			// Set button action events to the event methods of this class
			setControlButton(btnProcessedAllBeverages, iUnprocessedOrderPosition);
			
			textLabelsDisplayed[iUnprocessedOrderPosition] = new Label("");
			textLabelsDisplayed[iUnprocessedOrderPosition].setPrefWidth(prefColumnWidth);

			GridPane gridPaneOrder = new GridPane();
			gridPaneOrder.setVgap(10);
			gridPaneOrder.add(                       btnProcessedAllBeverages, 0, 0);
			gridPaneOrder.add( textLabelsDisplayed[iUnprocessedOrderPosition], 0, 1);
			gridPaneAll.add(gridPaneOrder, iUnprocessedOrderPosition, 0);
		}

		window.addNodesAsChildrenToRoot(gridPaneAll);
		
		// Put the final touches and display the window.
		window.start();
		
		//
		refreshDisplay();
	}
	
	private int findUnprocessedOrderCount() {
		int result = 0;
		for (Order order : orderQueue) {
			if (isDisplayOrder(order)) {
				result++;
			}
		}
		return result;
	}
	
	private ArrayList<Order> getOrdersToDisplay() {
		ArrayList<Order> result = new ArrayList<Order>();
		for (Order order : orderQueue) {
			if (isDisplayOrder(order)) {
				result.add(order);
			}
		}
		return result;
	}
	
	@Override
	void refreshDisplay() {
		
		// Count the number of orders that have not been processed yet 
		
		int nUnprocessedOrders = findUnprocessedOrderCount();
		
//		Order placeholderOrder = new Order(0); // FIX ME (not a placeholder here)
//		// One should use isDisplayOrder() to decide if the order is to be displayed
		ArrayList<Order> ordersToDisplay = getOrdersToDisplay();

		for (int iUnprocessedOrderPosition = 0; iUnprocessedOrderPosition < min(nUnprocessedOrders,ordersDisplayedCount); iUnprocessedOrderPosition++ ) {
			ordersDisplayed[    iUnprocessedOrderPosition] = ordersToDisplay.get(iUnprocessedOrderPosition);
			textLabelsDisplayed[iUnprocessedOrderPosition].setText(buildDisplayLabelString(ordersToDisplay.get(iUnprocessedOrderPosition)));
		}

		for (int iUnprocessedOrderPosition = nUnprocessedOrders; iUnprocessedOrderPosition < ordersDisplayedCount; iUnprocessedOrderPosition++ ) {
			textLabelsDisplayed[iUnprocessedOrderPosition].setText("");
//			ordersDisplayed[    iUnprocessedOrderPosition] = placeholderOrder;
		}
	}
	
	void setControlButton(Button button, int iOrderDisplayed) {
		final int iOdrDisp = iOrderDisplayed;
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventOrderProcessed(iOdrDisp);
			}
		});
	}

	private int min(int i1, int i2) {
		return (i1<i2) ? i1 : i2 ;
	}

}
