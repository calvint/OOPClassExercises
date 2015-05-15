package PartCFinal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CashierStation extends AbstractStation {

	protected Order orderDisplayed;
	protected Label textLabelDisplayed;

	CashierStation() {
		this(new JavaFXStage(new Stage()));
	}

	CashierStation(JavaFXStage window, AbstractStation ownerStation) {
		this(window);
		window.initOwner(ownerStation.getBaseWindow());
	}

	CashierStation(JavaFXStage window) {
		super(window);
		window.setObjectName("CashierStation");
		window.init();
		window.setWidth(140);
		window.setHeight(500);

		OrderStation.orderQueue.addObserver(this);
	}

	void eventOrderPoured(String command) {
		if (orderDisplayed == null)
			return;
		Order o = orderDisplayed;
		if (command.equals("Paid")) {
			orderDisplayed.doPay();
		}
		if (command.equals("Canceled")) {
			OrderStation.orderQueue.cancelOrder(orderDisplayed);
		}
		System.out.println(window.getObjectName() + ": Order: " + o.getOrderNumber() + " was " + command);
	}

	@Override
	void start() {
		// Order is displayed with a "Processed" button at top and order
		// description at the bottom.
		Button btnPayed = new Button("Paid");
		Button btnCanceled = new Button("Canceled");
		// Set button action events to the event methods of this class
		setControlButton(btnPayed, "Paid");
		setControlButton(btnCanceled, "Canceled");

		textLabelDisplayed = new Label("");
		textLabelDisplayed.setPrefWidth(120);

		GridPane gridPaneAll = new GridPane();
		gridPaneAll.setVgap(10);
		GridPane gridPaneOrder = new GridPane();
		gridPaneOrder.setHgap(10);
		gridPaneOrder.add(btnPayed, 0, 0);
		gridPaneOrder.add(btnCanceled, 1, 0);
		gridPaneAll.add(gridPaneOrder, 0, 0);
		gridPaneAll.add(textLabelDisplayed, 0, 1);

		window.addNodesAsChildrenToRoot(gridPaneAll);

		// Put the final touches and display the window.
		window.start();

		refreshDisplay();
	}

	void setControlButton(Button button, String command) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				eventOrderPoured(command);
			}
		});
	}

	@Override
	void refreshDisplay() {
		if (OrderStation.orderQueue.ordersInQueue() < 1) {
			textLabelDisplayed.setText("");
			return;
		}
		Order o = OrderStation.orderQueue.getOrderInList(0);
		int counter = 0;
		while (o.wasPayedFor()) {
			counter++;
			if (counter == OrderStation.orderQueue.ordersInQueue()) {
				break;
			}
			o = OrderStation.orderQueue.getOrderInList(counter);
		}
		orderDisplayed = o;
		if (orderDisplayed.wasPayedFor())
			orderDisplayed = null;

		String orderString = buildDisplayLabelString(orderDisplayed);

		textLabelDisplayed.setText(orderString);

	}

	String buildDisplayLabelString(Order order) {
		return order == null ? "" : order.getCostSummary();
	}

}
