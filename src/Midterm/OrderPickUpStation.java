package Midterm;
import java.util.ArrayList;

import javafx.stage.Stage;

public class OrderPickUpStation extends MultiOrderDisplayStation{
	
	private LogWriter log;
	private ArrayList<Order> paidForOrders = new ArrayList<Order>();
	
	OrderPickUpStation() {
		this(new JavaFXStage(new Stage()));
	}

	OrderPickUpStation(JavaFXStage window, AbstractStation ownerStation, LogWriter log) {
		this( window );
		window.initOwner(ownerStation.getBaseWindow());
		this.log = log;
	}

	OrderPickUpStation(JavaFXStage window) {
		super( window );
		prefColumnWidth = 110;
		window.setObjectName("OrderPickUpStation");
		window.init();
		window.setWidth( (prefColumnWidth+10)*ordersDisplayedCount-10 );
		window.setHeight(300);
	}

	@Override
	void eventOrderProcessed(int iOrderDisplayed) {
		if (ordersDisplayed[iOrderDisplayed] != null) {
			log.writeToLog(window.getObjectName() + ": order: " + Integer.toString(ordersDisplayed[iOrderDisplayed].getOrderNumber()) + " picked up based on button #" + iOrderDisplayed + " pressed.");
			orderQueue.remove(iOrderDisplayed);
			refreshDisplay();
		}
	}

	@Override
	boolean isDisplayOrder(Order order) {
		if (paidForOrders.contains(order) && orderQueue.contains(order)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	String buildDisplayLabelString(Order order) {
		return "Order " + Integer.toString(order.getOrderNumber()) + "  " + order.getDescription();
	}
	
	@Override
	void hasBeenRemoved(Order order) {
		orderQueue.remove(order);
		refreshDisplay();
	}

	@Override
	void isPaidFor(Order order) {
		paidForOrders.add(order);
		refreshDisplay();
	}
}
