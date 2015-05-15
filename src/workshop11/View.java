package workshop11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class View extends Application implements Observer{
	Controller controller;
	Model model;
	
	public View() {
		Model model = new Model();
		Controller controller = new Controller(model);
		controller.setView(this);
		model.addObserver(this);
		this.controller = controller;
		this.model = model;
	}
	
	//view
	private class AugmentedPane extends Pane {
		Position pos = new Position(0,0);
		int iVertex;
	}
	
	//view
    Pane rootPane = new Pane();
    Pane edgePane = new Pane(); // This is where all edges are going to go
	
    //view controller
    @Override
    public void init() {
    	this.controller.parseVerticesAndCreateAllEdges("graph.txt");
    }
    
    //view
    Node drawEdge(int iEdge) {
    	double x0 = model.edgeStartX(iEdge);
    	double y0 = model.edgeStartY(iEdge);
    	double x1 = model.edgeEndX(  iEdge);
    	double y1 = model.edgeEndY(  iEdge);
    	double w  = model.edgeWeight(iEdge);

    	return drawEdge(x0,y0,x1,y1,w,0.02);
    }

    //view
    Node drawEdge(double x0, double y0, double x1, double y1, double weight, double opacity) {
    	double dx = x1-x0;
    	double dy = y1-y0;
    	double length = Math.sqrt(dx*dx+dy*dy);

    	int i = 0; // For now???: a simple switch between the types of graphs
    	if (i == 0) {// Draw a straight edge
    		Line   line   = new Line();
    		line.setStartX(x0);
    		line.setStartY(y0);
    		line.setEndX(  x1);
    		line.setEndY(  y1);
    		line.setOpacity(opacity);
    		return line;
    	} 
    	else { // Draw a curve
	    	final int d1 = 10; // Constant for Arrow point offset
	    	final int d2 = 25; // Constant for arrow tail offset
	
	    	if ( length > 0 ) {
		    	dx = dx / length;
		    	dy = dy / length;
		    	
		    	CubicCurve  curve    = new CubicCurve();
		    	curve.setStartX(x0);
		    	curve.setStartY(y0);
		    	curve.setEndX(  x1);
		    	curve.setEndY(  y1);
		    	curve.setControlX1(x0+dy*40);
		    	curve.setControlY1(y0-dx*40);
		    	curve.setControlX2(x1-dx*80);
		    	curve.setControlY2(y1-dy*80);
		    	curve.setFill(null);
		    	curve.setStroke(Color.BLUE);
		    	curve.setOpacity(opacity);
		    	Line arrow1   = new Line();
		    	Line arrow2   = new Line();
		    	arrow1.setStartX(x1-dx*d2+dy*3);
		    	arrow2.setStartX(x1-dx*d2-dy*3);
		    	arrow1.setStartY(y1-dy*d2-dx*3);
		    	arrow2.setStartY(y1-dy*d2+dx*3);
		    	arrow1.setEndX(x1-dx*d1);
		    	arrow2.setEndX(x1-dx*d1);
		    	arrow1.setEndY(y1-dy*d1);
		    	arrow2.setEndY(y1-dy*d1);
		    	arrow1.setOpacity(opacity);
		    	arrow2.setOpacity(opacity);
		
		        Text text = new Text((x0+x1)/2+dy*12, (y0+y1)/2-dx*12, ""+weight);
		        //t.setFont(new Font(20));
		
		        Pane pane = new Pane();
		    	pane.getChildren().addAll(curve, arrow1, arrow2, text);
		    	return pane;
	    	} else return null;
	    }
    }
    
    //view
    Node drawNode(int iVertex) {
    	final AugmentedPane node = new AugmentedPane();
    	double circRadius = 8;
    	
    	node.setPrefSize(circRadius*2, circRadius*2);
    	node.relocate(
    			model.vertexPosX(iVertex)-circRadius, 
    			model.vertexPosY(iVertex)-circRadius  );

    	Circle circle = new Circle();
        circle.setCenterX(circRadius);
        circle.setCenterY(circRadius);
        circle.setRadius(circRadius);
        circle.setOpacity(0.05);

        node.iVertex = iVertex;
        node.setOnMousePressed( e -> {
        	if ( e.getClickCount() > 1 ) { // Multiple click
        		// Do nothing //System.out.println("Circle Multi click!!!");
        	} else { // Single click
        		System.out.println("Circle Single click!!! "+node.iVertex);
                node.pos.x = node.getLayoutX() - e.getSceneX();
                node.pos.y = node.getLayoutY() - e.getSceneY();
                node.setCursor(Cursor.MOVE);
        	}
        } );
        node.setOnMouseReleased( e -> {
        	node.setCursor(Cursor.HAND);
        } );
        node.setOnMouseDragged( e -> {
        	controller.mouseWasDragged(iVertex, node.getLayoutX(), node.getLayoutY(), e.getSceneX() + node.pos.x, e.getSceneY() + node.pos.y);
//        	double oldX = node.getLayoutX();
//        	double oldY = node.getLayoutY();
//        	double newX = e.getSceneX() + node.pos.x;
//        	double newY = e.getSceneY() + node.pos.y;
//        	controller.changeVertexPosX(iVertex, model.vertexPosX(iVertex) + newX - oldX);
//        	controller.changeVertexPosY(iVertex, model.vertexPosY(iVertex) + newY - oldY);
        	node.setLayoutX(e.getSceneX() + node.pos.x);
        	node.setLayoutY(e.getSceneY() + node.pos.y);
//        	
//        	// Redraw all edges
//        	controller.recomputeAllEdgesByDistance(); // Definitely inefficient
//        	edgePane.getChildren().removeAll(edgePane.getChildren());
//        	drawEdges();
        } );
        node.setOnMouseEntered( e -> {
        	node.setCursor(Cursor.HAND);
        } );

        node.getChildren().add(circle);

        Text text = new Text( 0, 0, model.vertexName(iVertex));
        text.relocate(
        		circRadius - text.getBoundsInLocal().getWidth()/2, 
        		circRadius - text.getBoundsInLocal().getHeight()/2);
        //t.setFont(new Font(20));
        node.getChildren().add(text);
        return node; 
    }
    
    //view
    void drawEdges() {
    	// NOTE, unlike the other draw functions, this one operates only on edgePane class member

        for (int iEdge = 0; iEdge < model.edgeCount(); iEdge++ ) {
        	edgePane.getChildren().add( drawEdge( iEdge ) );
        }

        // Generate an MST and plot it here
        EdgeArray edges = model.getMST_veryInefficient();
        for (int iEdge = 0; iEdge < edges.size(); iEdge++ ) {
        	edgePane.getChildren().add( drawEdge( 
        			model.vertexPosX(edges.get(iEdge).vertexIndex1),
        			model.vertexPosY(edges.get(iEdge).vertexIndex1),
        			model.vertexPosX(edges.get(iEdge).vertexIndex2),
        			model.vertexPosY(edges.get(iEdge).vertexIndex2),
        			edges.get(iEdge).weight, 0.6 ) );
        }
    }
    
    //view / controller
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minimum Spanning Tree");
        rootPane.getChildren().add(edgePane);

        drawEdges();

        for (int iVertex = 0; iVertex < model.vertexCount(); iVertex++ ) {
        	rootPane.getChildren().add( drawNode( iVertex ) );
        }

        rootPane.setOnMousePressed( e -> {
        	controller.mouseWasPressed(e);
//        	if ( e.getClickCount() > 1 ) { // Multiple click
//        		System.out.println("Multi click!!!"+e.getSceneX()+" "+e.getSceneY());
//
//        		int iVertex = model.vertexCount();
//        		controller.addVertex(""+iVertex, e.getSceneX(), e.getSceneY());
//            	rootPane.getChildren().add( drawNode( iVertex ) );
//
//        		// Redraw all edges
//            	controller.createAllDistanceEdges(); // Definitely inefficient
//            	edgePane.getChildren().removeAll(edgePane.getChildren());
//            	drawEdges();
//        	} else { // Single click
//        		// Do nothing // System.out.println("Single click!!!");
//        	}
        } );
        
        //add an h box for the buttons
        HBox hbox = new HBox();
        
        //add undo/redo buttons
        Button undoButton = new Button("Undo");
        
        undoButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("undo pressed");
            }
        });
        
        Button redoButton = new Button("Redo");
        
        redoButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("redo pressed");
            }
        });
        
        hbox.getChildren().addAll(undoButton, redoButton);
        rootPane.getChildren().add(hbox);
        
        
        primaryStage.setScene(new Scene(rootPane, 400, 400));
        primaryStage.show();
    }

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			int iVertex = (Integer) arg;
			rootPane.getChildren().add( drawNode( iVertex ) );
		}
		edgePane.getChildren().removeAll(edgePane.getChildren());
    	drawEdges();
	}
}
