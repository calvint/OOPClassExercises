package workshop11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.input.MouseEvent;
import workshop11.Edge;

public class Controller {
	View view;
	Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	

//	controller
	void parseVerticesAndCreateAllEdges(String filename) {

		try {
			BufferedReader reader;
			reader = new BufferedReader( new FileReader( filename ) );
			try {
				parseVertices(reader);

				model.createAllDistanceEdges(true);
				
				reader.close();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	controller
	private void parseVertices(BufferedReader reader) {
		try {
			String vertex;
			while ( (vertex=reader.readLine()) != null && !vertex.equals("----------") ) {
	
				Scanner in = new Scanner(vertex);
				in.useDelimiter("\\s+");
				String   vertexName;
				double   locationX;
				double   locationY;
				vertexName = in.next();
				locationX  = in.nextDouble();
				locationY  = in.nextDouble();
				in.close();
	
				model.addVertex(vertexName, locationX, locationY);
			}
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//controller
	private void parseEdges(BufferedReader reader) {
		try {
			String edge;
			while ( (edge=reader.readLine()) != null ) {

				Scanner in = new Scanner(edge);
				in.useDelimiter("\\s+");
				String   vertexName1;
				String   vertexName2;
				double   weight;
				vertexName1 = in.next();
				vertexName2 = in.next();
				weight      = in.nextDouble();
				in.close();

				model.addEdge(vertexName1, vertexName2, weight);
			}
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addVertex(String name, double X, double Y) {
		model.addVertex(name, X, Y);
	}

	public void mouseWasPressed(MouseEvent e) {
		if ( e.getClickCount() > 1 ) { // multi click
    		System.out.println("Multi click!!!"+e.getSceneX()+" "+e.getSceneY());
    		int iVertex = model.vertexCount() + 1;
    		model.addVertex(""+iVertex, e.getSceneX(), e.getSceneY());
        	model.createAllDistanceEdges(false);
    	} else { // Single click
    		  System.out.println("Single click!!!");
    	}
		
	}

	public void mouseWasDragged( int iVertex, double oldX, double oldY, double newX, double newY) {
		model.vertexPosSetX(iVertex, model.vertexPosX(iVertex) + newX - oldX);
		model.vertexPosSetY(iVertex, model.vertexPosY(iVertex) + newY - oldY);
		model.recomputeAllEdgesByDistance(); 
	}
}
