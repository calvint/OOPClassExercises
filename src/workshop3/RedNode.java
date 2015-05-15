package workshop3;

public class RedNode extends xyPair{
	private double x;
	private double y;
	
	public RedNode(double d, double e) {
		super(d, e);
	}

	public String toString() {
		return "R(" + this.getX() + "," + this.getY() + ") ";
	}
}
