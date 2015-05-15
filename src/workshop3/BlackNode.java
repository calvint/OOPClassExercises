package workshop3;

public class BlackNode extends xyPair {
	
	public BlackNode(double d, double e) {
		super(d, e);
		System.out.println("something");
	}
	
	public String toString() {
		return "B(" + this.getX() + "," + this.getY() + ") ";
	}
}
