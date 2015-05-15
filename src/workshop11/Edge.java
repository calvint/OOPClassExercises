package workshop11;

// Hold edge (v1, v2) and its weight
public class Edge {
	int vertexIndex1;
	int vertexIndex2;
	double weight;
	Edge(int v1, int v2, double weight) {
		vertexIndex1 = v1;
		vertexIndex2 = v2;
		this.weight  = weight;
	}
}