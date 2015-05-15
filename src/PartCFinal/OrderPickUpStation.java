package PartCFinal;
import javafx.stage.Stage;

public class OrderPickUpStation extends MultiOrderDisplayStation {

	OrderPickUpStation() {
		this(new JavaFXStage(new Stage()));
	}

	OrderPickUpStation(JavaFXStage window, AbstractStation ownerStation) {
		this(window);
		window.initOwner(ownerStation.getBaseWindow());
	}

	OrderPickUpStation(JavaFXStage window) {
		super(window);
		prefColumnWidth = 110;
		window.setObjectName("OrderPickUpStation");
		window.init();
		window.setWidth((prefColumnWidth + 10) * ordersDisplayedCount - 10);
		window.setHeight(300);
	}

	@Override
	void eventOrderProcessed(int iOrderDisplayed) {
		Order order = ordersDisplayed[iOrderDisplayed];
		if (order == null)
			return;

		System.out.println(window.getObjectName() + ": order: " + order.getOrderNumber() + " picked up based on button #" + iOrderDisplayed + " pressed.");

		OrderStation.orderQueue.orderPickedUp(order);
	}

	@Override
	boolean isDisplayOrder(Order order) {
		return order.wasPoured() && order.wereCondimentsAdded() && order.wasPayedFor();
	}

	@Override
	String buildDisplayLabelString(Order order) {
		return order.getIngredientSummary(); //
	}

}
