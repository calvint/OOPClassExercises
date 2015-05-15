package workshop10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class EmployeeTreeViewer extends Application {
	BufferedReader reader = null;
	Scene mainScene = null;
    
 
    final String[] viewOptions = new String[] {
    };
 
   
 
    public static void main(String[] args) {
        launch();
    }
    
    private void parseFile(BufferedReader reader) {
    	
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.OLDLACE);
        mainScene = scene;
        
        stage.setTitle("File Chooser Sample");
        final FileChooser fileChooser = new FileChooser();
        
        MenuBar menuBar = new MenuBar();
 
        // --- Menu File
        Menu menuFile = new Menu("File");
        
        MenuItem loadFile = new MenuItem("Load File");
        loadFile.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        final Stage myStage = stage;
        loadFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
            	File file = fileChooser.showOpenDialog(myStage);
                if (file != null) {
                	String filePath = file.toString();
                	try {
						reader = new BufferedReader( new FileReader(filePath) );
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	//parse the file and create composite pattern
                	Manager rootManager = ReportsParse.reportsParse(reader);
                	
                	//Traverse composite pattern and populate a javaFX node structure
                	TreeItem<String> rootItem = new TreeItem<String>(rootManager.getName());
                	ArrayList<TreeItem<String>> list = new ArrayList<TreeItem<String>>();
                	list.add(rootItem);
//                	CompositeIterator iterator = new CompositeIterator(rootManager.createIterator());
                	CompositeIterator iterator = new CompositeIterator(rootManager.createIterator());
                	while (iterator.hasNext()) {
                		//check the stack length in the iterator and change lists' length to match
                		if (list.size() > iterator.getDepth()+1) {
                			while (list.size() > iterator.getDepth()) {
                				list.remove(list.size()-1);
                			}
                		}
                		//Make a new tree item
                		Worker worker = (Worker) iterator.next();
                		TreeItem<String> item = new TreeItem<String>(worker.getName());
                		//Add it to it's parent
                		list.get(list.size()-1).getChildren().add(item);
                		//Add the tree item to the stack if it is a manager
                		if (worker instanceof Manager ) {
                			list.add(item);
                		}
                	}
                	//display the node structure
                	TreeView<String> tree = new TreeView<String> (rootItem); 
                	((VBox) mainScene.getRoot()).getChildren().add(tree);
                }
            }
	    });
        menuFile.getItems().addAll(loadFile);
 
        menuBar.getMenus().addAll(menuFile);
 
 
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
 
        stage.setScene(scene);
        stage.show();
    }
}