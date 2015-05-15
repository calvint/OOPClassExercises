package PartCFinal;
public class Soy extends CondimentDecorator {

	public Soy(Beverage b) {
		super(b);
	}

	@Override
	public double cost() {
		return beverage.cost() + .15;
	}

	@Override
	public String getName() {
		return "Soy " + beverage.getName();
	}

}
