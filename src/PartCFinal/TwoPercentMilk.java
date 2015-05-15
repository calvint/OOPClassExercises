package PartCFinal;
public class TwoPercentMilk extends CondimentDecorator {

	public TwoPercentMilk(Beverage b) {
		super(b);
	}

	@Override
	public double cost() {
		return beverage.cost() + .25;
	}

	@Override
	public String getName() {
		return "Steamed Milk (2%) " + beverage.getName();
	}

}
