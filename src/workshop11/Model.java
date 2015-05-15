package workshop11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.TreeMap;

import workshop11.Edge;
import workshop11.EdgeArray;

public class Model extends Observable {
	
	//model
	private ArrayList<String>         vertexNameArray     = new ArrayList<String>       ();
	private TreeMap<String, Integer>  vertexIndexMap      = new TreeMap<String, Integer>();
	private ArrayList<Position>       vertexPositionArray = new ArrayList<Position>     ();
	private EdgeArray                 edgeArray           = new EdgeArray               ();
	
	public void setEdgeArray(EdgeArray edgeArray) {
		this.edgeArray = edgeArray;
	}
	
	public EdgeArray getEdgeArray() {
		return this.edgeArray;
	}
	
	//model
	void createAllDistanceEdges(Boolean parsing) {
		edgeArray = new EdgeArray();
		for (     int i0 =    0; i0 < vertexCount(); i0++) {
			double x0 = vertexPosX(i0);
			double y0 = vertexPosY(i0);
			for ( int i1 = i0+1; i1 < vertexCount(); i1++) {
				double x1 = vertexPosX(i1);
				double y1 = vertexPosY(i1);
		    	double dx = x1-x0;
		    	double dy = y1-y0;
		    	double length = Math.sqrt(dx*dx+dy*dy);

				edgeArray.add(new Edge(i0,i1,length));
			}
		}
		this.setChanged();
		if (parsing == false){
		this.notifyObservers(this.vertexCount() - 1);
		}
	}
	
	//model
	void recomputeAllEdgesByDistance() {
		for (     int iEdge =    0; iEdge < edgeCount(); iEdge++) {
			double x0 = vertexPosX(edgeStart(iEdge));
			double y0 = vertexPosY(edgeStart(iEdge));
			double x1 = vertexPosX(edgeEnd(  iEdge));
			double y1 = vertexPosY(edgeEnd(  iEdge));
	    	double dx = x1-x0;
	    	double dy = y1-y0;
	    	double length = Math.sqrt(dx*dx+dy*dy);
	    	
	    	edgeArray.get(iEdge).weight = length;
	    	//System.out.println("Edge "+edgeStart(iEdge)+" "+edgeEnd(iEdge)+" "+length);
	    	this.setChanged();
	    	this.notifyObservers();
		}
	}
	
	
	// model
	// Append a vertex to the end of appropriate graph collections
	public void addVertex(String name, double x, double y) {
		int count = vertexCount();
		vertexNameArray.add(name);
		vertexIndexMap.put(name, count);
		vertexPositionArray.add(new Position(x,y));
	}

	//model
	// Append edge to the end of appropriate graph collection
	public void addEdge(String v1, String v2, double weight) {
		try {
			edgeArray.add(new Edge(vertexIndexMap.get(v1),vertexIndexMap.get(v2),weight));
		} catch (Exception E) {
			System.out.println("Warning: Vertices of the edge not found!!!");
		}
	}
	
	//model
	public int vertexCount() {
		return vertexNameArray.size();
	}
	
	//model
	public int edgeCount() {
		return edgeArray.size();
	}
	
	//model
	public double vertexPosX(int index) {
		return vertexPositionArray.get(index).x;
	}
	public double vertexPosY(int index) {
		return vertexPositionArray.get(index).y;
	}
	public void vertexPosSetX(int index, double x) {
		vertexPositionArray.get(index).x = x;
	}
	public void vertexPosSetY(int index, double y) {
		vertexPositionArray.get(index).y = y;
	}
	
	//model
	String vertexName(int index) {
		return vertexNameArray.get(index);
	}
	
	//model
	protected int edgeStart(int index) { // Index of starting iVertex
		return edgeArray.get(index).vertexIndex1;
	}
	protected int edgeEnd(int index) { // Index of ending iVertexS
		return edgeArray.get(index).vertexIndex2;
	}
	public double edgeStartX(int index) {
		return vertexPositionArray.get(edgeArray.get(index).vertexIndex1).x;
	}
	public double edgeStartY(int index) {
		return vertexPositionArray.get(edgeArray.get(index).vertexIndex1).y;
	}
	public double edgeEndX(int index) {
		return vertexPositionArray.get(edgeArray.get(index).vertexIndex2).x;
	}
	public double edgeEndY(int index) {
		return vertexPositionArray.get(edgeArray.get(index).vertexIndex2).y;
	}
	public double edgeWeight(int index) {
		return edgeArray.get(index).weight;
	}

	
	
	
	
	//model
	EdgeArray getMST_veryInefficient() {
		EdgeArray mst = new EdgeArray();
		ArrayList<Integer> subgraphIDs = new ArrayList<Integer>();
		for( int iVertex = 0; iVertex < vertexCount(); iVertex++ ) {
			subgraphIDs.add(iVertex);
		}

		int iMinEdge;
		do {
			iMinEdge = -1;
			double minWeight = 1e42;
			for( int iEdge = 0; iEdge < edgeCount(); iEdge++ ) {
				int iVertex0 = edgeStart(iEdge);
				int iVertex1 = edgeEnd(  iEdge);
				if (    subgraphIDs.get(iVertex0) != subgraphIDs.get(iVertex1) &&
						minWeight > edgeWeight(iEdge)                             ) {
					iMinEdge  = iEdge;
					minWeight = edgeWeight(iEdge);
				}
			}
	
			if (iMinEdge >= 0) {
				//System.out.println("Edge "+edgeStart(iMinEdge)+" "+edgeEnd(iMinEdge)+" "+edgeWeight(iMinEdge));
				int iVertex0 = edgeStart(iMinEdge);
				int iVertex1 = edgeEnd(  iMinEdge);
				mst.add(new Edge(iVertex0, iVertex1, edgeWeight(iMinEdge)));
				int newSubgraphID = subgraphIDs.get(iVertex0);
				int oldSubgraphID = subgraphIDs.get(iVertex1);
				for( int iVertex = 0; iVertex < vertexCount(); iVertex++ ) {
					if (subgraphIDs.get(iVertex) == oldSubgraphID) subgraphIDs.set(iVertex, newSubgraphID);
				}
			}
		} while (iMinEdge >= 0);
		//System.out.println("end");

		return mst;
	}
}
