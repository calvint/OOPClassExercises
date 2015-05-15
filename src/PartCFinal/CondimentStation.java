package PartCFinal;
import javafx.stage.Stage;

public class CondimentStation extends MultiOrderDisplayStation {

	CondimentStation() {
		this(new JavaFXStage(new Stage()));
	}

	CondimentStation(JavaFXStage window, AbstractStation ownerStation) {
		this(window);
		window.initOwner(ownerStation.getBaseWindow());
	}

	CondimentStation(JavaFXStage window) {
		super(window);
		window.setObjectName("CondimentStation");
		window.init();
		window.setWidth((prefColumnWidth + 10) * ordersDisplayedCount - 10);
		window.setHeight(300);
	}

	@Override
	void eventOrderProcessed(int iOrderDisplayed) {
		Order order = ordersDisplayed[iOrderDisplayed];
		if (order == null)
			return;

		System.out.println(window.getObjectName() + ": You added condiments for order: " + order.getOrderNumber() + " based on button #" + iOrderDisplayed + " pressed.");

		order.doCondiments();
	}

	@Override
	boolean isDisplayOrder(Order order) {
		return order.wasPoured() && !order.wereCondimentsAdded();
	}

	@Override
	String buildDisplayLabelString(Order order) {
		return order.getIngredientSummary();
	}

}
