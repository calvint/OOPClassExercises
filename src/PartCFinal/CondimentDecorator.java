package PartCFinal;
public abstract class CondimentDecorator extends Beverage {
	public Beverage beverage;

	public CondimentDecorator(Beverage b) {
		beverage = b;
	}

	public abstract double cost();

	public abstract String getName();

	public String getCondiments() {
		return getName() + "\n" + beverage.getCondiments();
	}
}
