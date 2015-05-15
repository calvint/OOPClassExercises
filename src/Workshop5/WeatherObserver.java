package Workshop5;

import java.util.ArrayList;

public class WeatherObserver implements Observer {
	WeatherDataHub hub;
	Double temperature;
	Double windSpeed;
	JavaFXDialogBoxChild child;
	
	public WeatherObserver(WeatherDataHub hub, JavaFXDialogBoxChild child) {
		this.child = child;
		this.hub = hub;
		hub.registerObserver(this);
	}
	
	public void getDataFromHub() {
		ArrayList<Double> data = hub.getData();
		this.temperature = data.get(0);
		this.windSpeed = data.get(1);
	}
	
	public void update() {
		if (this.child.listenerChkBx.isSelected()) {
			this.getDataFromHub();
			this.child.updateListnerLabel(this.temperature, this.windSpeed);
		}
	}
}
