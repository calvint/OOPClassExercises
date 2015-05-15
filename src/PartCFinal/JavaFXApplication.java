package PartCFinal;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {

	protected String objectName = "JavaFXApplication";
	protected OrderStation orderStationDialog;
	protected PourBeverageStation pourBeverageDialog;
	protected CashierStation cashierDialog;
	protected CondimentStation condimentDialog;
	protected OrderPickUpStation orderPickUpDialog;

	public static void main(String[] args) {
		System.out.println("Launching JavaFX application.");

		// Start the JavaFX application by calling launch().
		launch(args);
	}

	// Override the init() method.
	public void init() {
		System.out.println(objectName + ": Inside the init() method.");
	}

	// Override the start() method.
	public void start(Stage myStage) {

		System.out.println(objectName + ": Inside the start() method.");
		orderStationDialog = new OrderStation();
		pourBeverageDialog = new PourBeverageStation(new JavaFXStage(new Stage()), orderStationDialog);
		cashierDialog = new CashierStation(new JavaFXStage(new Stage()), orderStationDialog);
		condimentDialog = new CondimentStation(new JavaFXStage(new Stage()), orderStationDialog);
		orderPickUpDialog = new OrderPickUpStation(new JavaFXStage(new Stage()), orderStationDialog);

		pourBeverageDialog.start();
		cashierDialog.start();
		condimentDialog.start();
		orderPickUpDialog.start();
		orderStationDialog.start();
	}

	// Override the stop() method.
	public void stop() {
		System.out.println(objectName + ": Inside the stop() method.");
	}

}
