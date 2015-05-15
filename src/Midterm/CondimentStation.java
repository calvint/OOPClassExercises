package Midterm;
import javafx.stage.Stage;

public class CondimentStation extends MultiOrderDisplayStation{
	
	private LogWriter log;
	private OrderPickUpStation nextStation;
	
	CondimentStation() {
		this(new JavaFXStage(new Stage()));
	}

	CondimentStation(JavaFXStage window, AbstractStation ownerStation, LogWriter log) {
		this( window );
		window.initOwner(ownerStation.getBaseWindow());
		this.log = log;
	}

	CondimentStation(JavaFXStage window) {
		super( window );
		window.setObjectName("CondimentStation");
		window.init();
		window.setWidth( (prefColumnWidth+10)*ordersDisplayedCount-10 );
		window.setHeight(300);
	}
	
	public void initStation(OrderPickUpStation station) {
		nextStation = station;
	}
	
	@Override
	void eventOrderProcessed(int iOrderDisplayed) {
		if (ordersDisplayed[iOrderDisplayed] != null) {
			Order order = ordersDisplayed[iOrderDisplayed];
			log.writeToLog(window.getObjectName() + ": You poured beverages for order: " + Integer.toString(order.getOrderNumber()) + " based on button #" + iOrderDisplayed + " pressed."); 
			nextStation.addOrderToQueue(order);
			// Logic for Order update to filled here
			orderQueue.remove(iOrderDisplayed);
			// Redisplay unprocessed orders (without this order)
			refreshDisplay();
		}
	}

	@Override
	boolean isDisplayOrder(Order order) {
		return true;
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
		
	}

}
