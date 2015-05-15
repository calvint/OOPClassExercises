package PartCFinal;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

public class OrderQueue {
	private int totalOrderCount;
	private ArrayList<Order> orders;
	private ArrayList<Observer> observers;
	private BufferedWriter logWriter;

	public OrderQueue(String logPath) {
		orders = new ArrayList<Order>();
		observers = new ArrayList<Observer>();
		try {
			logWriter = new BufferedWriter(new FileWriter(new File(logPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int ordersInQueue() {
		return orders.size();
	}

	public void queueOrder(Order o) {
		orders.add(o);
		notifyObservers();
	}

	public void cancelOrder(Order o) {
		orders.remove(o);
		notifyObservers();
	}

	public Order getOrderInList(int i) {
		return orders.get(i);
	}

	public void orderPickedUp(Order o) {
		orders.remove(o);
		notifyObservers();
		try {
			logWriter.write(o.toString() + (System.getProperty("os.name").toLowerCase().contains("win") ? "\r\n" : "\n"));
			logWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Order newOrder() {
		Order o = new Order();
		o.setOrderNumber(totalOrderCount++);
		return o;
	}

	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(null, null);
		}
	}

	public void addObserver(Observer o) {
		observers.add(o);
	}
}
