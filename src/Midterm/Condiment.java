package Midterm;

public class Condiment extends Drink {
	Drink drink;
	double cost;
	String description;
	
	public Condiment(Drink drink) {
		this.drink = drink;
	}
	
	public double cost() {
		return cost + drink.cost();
	}
	
	public String getDescription() {
		return  drink.getDescription() + description;
	}
}
