package Workshop5;

import java.util.ArrayList;

public class WeatherDataHub implements Subject {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	Double temperature;
	Double windSpeed;
	
	public WeatherDataHub(Double temperature, Double windSpeed) {
		this.temperature = temperature;
		this.windSpeed = windSpeed;
	}	
	
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
	
	public ArrayList<Double> getData() {
		ArrayList<Double> result = new ArrayList<Double>();
		result.add(temperature);
		result.add(windSpeed);
		return result;
	}
	
	public void setData(Double temperature, Double windSpeed) {
		this.temperature = temperature;
		this.windSpeed = windSpeed;
		this.notifyObservers();
	}
}
