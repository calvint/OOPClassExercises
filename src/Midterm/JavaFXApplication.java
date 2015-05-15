package Midterm;

import java.util.ArrayList;

import javafx.application.*; 
import javafx.stage.*; 

public class JavaFXApplication extends Application { 
	
	private LogWriter logWriter = new LogWriter("logfile.txt");
	
	protected String objectName = "JavaFXApplication";
	protected        OrderStation  orderStationDialog;
	protected PourBeverageStation  pourBeverageDialog;
	protected      CashierStation       cashierDialog;
	protected    CondimentStation     condimentDialog;
	protected  OrderPickUpStation   orderPickUpDialog;

	public static void main(String[] args) { 
//		logWriter.writeToLog("Launching JavaFX application."); 

		// Start the JavaFX application by calling launch(). 
		launch(args);   
	} 
	
	// Override the init() method. 
	public void init() { 
		logWriter = new LogWriter("logfile.txt");
		logWriter.writeToLog(objectName + ": Inside the init() method."); 
	} 
	
	// Override the start() method. 
	public void start(Stage myStage) { 

		logWriter.writeToLog(objectName + ": Inside the start() method."); 

		orderStationDialog = new OrderStation(logWriter);
		pourBeverageDialog = new PourBeverageStation(new JavaFXStage(new Stage()), orderStationDialog, logWriter);
		cashierDialog      = new      CashierStation(new JavaFXStage(new Stage()), orderStationDialog, logWriter);
		condimentDialog    = new    CondimentStation(new JavaFXStage(new Stage()), orderStationDialog, logWriter);
		orderPickUpDialog  = new  OrderPickUpStation(new JavaFXStage(new Stage()), orderStationDialog, logWriter);
		
		orderStationDialog.initStations(cashierDialog, pourBeverageDialog);
		
		ArrayList<MultiOrderDisplayStation> stationCollection = new ArrayList<MultiOrderDisplayStation>();
		stationCollection.add( (MultiOrderDisplayStation) pourBeverageDialog);
		stationCollection.add( (MultiOrderDisplayStation) condimentDialog);
		stationCollection.add( (MultiOrderDisplayStation) orderPickUpDialog);
		cashierDialog.initStations(stationCollection);
		
		pourBeverageDialog.initStation(condimentDialog);
		
		condimentDialog.initStation(orderPickUpDialog);
		
		orderStationDialog.start();
		pourBeverageDialog.start();
		cashierDialog     .start();
		condimentDialog   .start();
		orderPickUpDialog .start();
	} 

	// Override the stop() method. 
	public void stop() { 
		logWriter.writeToLog(objectName + ": Inside the stop() method."); 
	} 
}

