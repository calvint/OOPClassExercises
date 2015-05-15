package Midterm;
import javafx.stage.Stage;

public class PourBeverageStation extends MultiOrderDisplayStation{
	
	private LogWriter log;
	private CondimentStation nextStation;
	
	PourBeverageStation() {
		this(new JavaFXStage(new Stage()));
	}

	PourBeverageStation(JavaFXStage window, AbstractStation ownerStation, LogWriter log) {
		this( window );
		window.initOwner(ownerStation.getBaseWindow());
		this.log = log;
	}

	PourBeverageStation(JavaFXStage window) {
		super( window );
		window.setObjectName("PourBeverageStation");
		window.init();
		window.setWidth( (prefColumnWidth+10)*ordersDisplayedCount-10 );
		window.setHeight(300);
	}
	
	public void initStation(CondimentStation station) {
		nextStation = station;
	}
	
	@Override
	void eventOrderProcessed(int iOrderDisplayed) {
		if (ordersDisplayed[iOrderDisplayed] != null) {
			Order order = ordersDisplayed[iOrderDisplayed];
			log.writeToLog(window.getObjectName() + ": You poured beverages for order: " + Integer.toString(order.orderNumber) + " based on button #" + iOrderDisplayed + " pressed."); 
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
