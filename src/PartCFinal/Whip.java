package PartCFinal;
public class Whip extends CondimentDecorator {

	public Whip(Beverage b) {
		super(b);
	}

	@Override
	public double cost() {
		return beverage.cost() + .10;
	}

	@Override
	public String getName() {
		return "Whipped " + beverage.getName();
	}

}
