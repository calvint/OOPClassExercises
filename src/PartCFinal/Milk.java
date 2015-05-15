package PartCFinal;
public class Milk extends CondimentDecorator {

	public Milk(Beverage b) {
		super(b);
	}

	@Override
	public double cost() {
		return beverage.cost() + .10;
	}

	@Override
	public String getName() {
		return "Steamed Milk " + beverage.getName();
	}

}
