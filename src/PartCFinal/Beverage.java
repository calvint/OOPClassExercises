package PartCFinal;
public abstract class Beverage {

	public Beverage() {
	}

	public abstract double cost();

	public abstract String getName();

	public String getCondiments() {
		return "";
	}

}
