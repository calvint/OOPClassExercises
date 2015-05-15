package workshop1;

public class colorClass {
	String color;
	int red;
	int green;
	int blue;
	
	public colorClass(String color, int red, int green, int blue) {
		this.color = color;
		this.red = red;
		this.green= green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
	
	public int greyscale() {
		return ((red+green+blue)/2);
	}
	
	public static void main(String[] args) {
		colorClass black = new colorClass("black", 0, 0, 0);
		colorClass white = new colorClass("white", 255, 255, 255);
		colorClass red = new colorClass("red", 255, 0, 0);
		colorClass yellow = new colorClass("yellow", 255, 255, 0);
	}
}
