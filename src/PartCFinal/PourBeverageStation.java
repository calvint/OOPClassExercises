package PartCFinal;
import javafx.stage.Stage;

public class PourBeverageStation extends MultiOrderDisplayStation {

	PourBeverageStation() {
		this(new JavaFXStage(new Stage()));
	}

	PourBeverageStation(JavaFXStage window, AbstractStation ownerStation) {
		this(window);
		window.initOwner(ownerStation.getBaseWindow());
	}

	PourBeverageStation(JavaFXStage window) {
		super(window);
		window.setObjectName("PourBeverageStation");
		window.init();
		window.setWidth((prefColumnWidth + 10) * ordersDisplayedCount - 10);
		window.setHeight(300);
	}

	@Override
	void eventOrderProcessed(int iOrderDisplayed) {
		Order order = ordersDisplayed[iOrderDisplayed];
		if (order == null)
			return;

		System.out.println(window.getObjectName() + ": You poured beverages for order: " + order.getOrderNumber() + " based on button #" + iOrderDisplayed + " pressed.");

		order.doPour();

	}

	@Override
	boolean isDisplayOrder(Order order) {
		return !order.wasPoured();
	}

	@Override
	String buildDisplayLabelString(Order order) {
		return order.getIngredientSummary();
	}

}
