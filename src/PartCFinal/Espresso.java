package PartCFinal;
public class Espresso extends Beverage {

	public Espresso() {
	}

	@Override
	public double cost() {
		return 1.99;
	}

	@Override
	public String getName() {
		return "Espresso";
	}

}
