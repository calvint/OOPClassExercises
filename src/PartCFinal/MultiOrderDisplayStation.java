package PartCFinal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

abstract public class MultiOrderDisplayStation extends AbstractStation {

	protected Order ordersDisplayed[];
	protected Label textLabelsDisplayed[];
	protected int ordersDisplayedCount = 4;
	protected int prefColumnWidth = 100;

	MultiOrderDisplayStation(JavaFXStage window) {
		super(window);
		window.setObjectName("MultiOrderDisplayStation");
		// window.init();

		// set the displayed orders
		OrderStation.orderQueue.addObserver(this);
		ordersDisplayed = new Order[ordersDisplayedCount];
		textLabelsDisplayed = new Label[ordersDisplayedCount];
	}

	void eventOrderProcessed(int iOrderDisplayed) {
		Order order = ordersDisplayed[iOrderDisplayed];

		System.out.println(window.getObjectName() + ": You processed beverages for order: " + order.getOrderNumber() + " based on button #" + iOrderDisplayed + " pressed.");

		// Logic for Order update to filled here

		// Redisplay unprocessed orders (without this order)
		refreshDisplay();
	}

	abstract boolean isDisplayOrder(Order order);

	abstract String buildDisplayLabelString(Order order);

	@Override
	void start() { // set up the display
		GridPane gridPaneAll = new GridPane();
		gridPaneAll.setHgap(10);

		// GridPane used to assign places for orders horizontally
		// Each order is displayed with a "Processed" button at top and order
		// description at the bottom.
		for (int iUnprocessedOrderPosition = 0; iUnprocessedOrderPosition < ordersDisplayedCount; iUnprocessedOrderPosition++) {
			Button btnProcessedAllBeverages = new Button("Processed");
			// Set button action events to the event methods of this class
			setControlButton(btnProcessedAllBeverages, iUnprocessedOrderPosition);

			textLabelsDisplayed[iUnprocessedOrderPosition] = new Label("");
			textLabelsDisplayed[iUnprocessedOrderPosition].setPrefWidth(prefColumnWidth);

			GridPane gridPaneOrder = new GridPane();
			gridPaneOrder.setVgap(10);
			gridPaneOrder.add(btnProcessedAllBeverages, 0, 0);
			gridPaneOrder.add(textLabelsDisplayed[iUnprocessedOrderPosition], 0, 1);
			gridPaneAll.add(gridPaneOrder, iUnprocessedOrderPosition, 0);
		}

		window.addNodesAsChildrenToRoot(gridPaneAll);

		// Put the final touches and display the window.
		window.start();

		//
		refreshDisplay();
	}

	@Override
	void refreshDisplay() {
		ordersDisplayed = new Order[ordersDisplayedCount];
		for (int i = 0; i < ordersDisplayedCount; i++) {
			textLabelsDisplayed[i].setText("");
		}
		OrderQueue queue = OrderStation.orderQueue;
		int displayCount = 0;
		int orderCount = queue.ordersInQueue();
		while (orderCount > 0) {
			Order o = queue.getOrderInList(queue.ordersInQueue() - orderCount);
			orderCount--;
			if (isDisplayOrder(o)) {
				ordersDisplayed[displayCount] = o;
				textLabelsDisplayed[displayCount].setText(o.getIngredientSummary());
				displayCount++;
				if (displayCount == ordersDisplayed.length)
					break;
			}
		}

	}

	void setControlButton(Button button, int iOrderDisplayed) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventOrderProcessed(iOrderDisplayed);
			}
		});
	}

}
