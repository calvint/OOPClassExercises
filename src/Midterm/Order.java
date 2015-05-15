package Midterm;
import java.util.ArrayList;

public class Order {
	
	ArrayList<Drink> listOfDrinks = new ArrayList<Drink>();
	int orderNumber;
	
	public void addDrink(Drink drink) {
		listOfDrinks.add(drink);
	}
	
	public Order(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public double getCost() {
		double result = 0;
		for (Drink drink: listOfDrinks) {
			result += drink.cost();
		}
		return result;
	}
	
	public String getDescription() {
		String result = "";
		for (Drink drink : listOfDrinks) {
			result = result + drink.getDescription();
		}
		return result;
	}
	
//	void setOrderNumber( int orderNumber ) {
//		this.orderNumber = orderNumber;
//	}
}
