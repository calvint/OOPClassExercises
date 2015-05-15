package PartCFinal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class Order {

	private int orderNumber = 0;
	private ArrayList<Beverage> beverages;
	private boolean condimentsAdded;
	private boolean poured;
	private boolean payedFor;

	public Order() {
		beverages = new ArrayList<Beverage>();
	}

	public void addBeverage(Beverage b) {
		beverages.add(b);
	}

	public void doPay() {
		payedFor = true;
		OrderStation.orderQueue.notifyObservers();
	}

	public void doPour() {
		poured = true;
		OrderStation.orderQueue.notifyObservers();
	}

	public void doCondiments() {
		condimentsAdded = true;
		OrderStation.orderQueue.notifyObservers();
	}

	public boolean wasPayedFor() {
		return payedFor;
	}

	public boolean wasPoured() {
		return poured;
	}

	public boolean wereCondimentsAdded() {
		return condimentsAdded;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getCost() {
		double total = 0;
		for (Beverage b : beverages) {
			total += b.cost();
		}
		return total;
	}

	public String getCostSummary() {
		String summary = "Order: " + orderNumber;
		for (Beverage b : beverages) {
			summary += "\n$" + b.cost() + " " + b.getName();
		}
		summary += "\nTotal: $" + getCost();
		return summary;
	}

	public String getIngredientSummary() {
		String summary = "Order: " + orderNumber;
		for (Beverage b : beverages) {
			summary += "\n" + " " + b.getName();
		}
		return summary;
	}

	public String toString() {
		String log = (new Timestamp(Calendar.getInstance().getTime().getTime())).toString() + " Order " + orderNumber + ": ";
		log += "$" + getCost() + ", ";
		for (Beverage b : beverages) {
			log += b.getName() + ", ";
		}
		log = log.substring(0, log.length() - 2);
		return log;
	}
}
