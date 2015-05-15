package PartCFinal;
public class Mocha extends CondimentDecorator {

	public Mocha(Beverage b) {
		super(b);
	}

	@Override
	public double cost() {
		return beverage.cost() + .20;
	}

	@Override
	public String getName() {
		return "Mocha " + beverage.getName();
	}

}
