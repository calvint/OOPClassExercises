package PartCFinal;
import java.util.Observable;
import java.util.Observer;

public abstract class AbstractStation implements Observer {

	protected JavaFXStage window;

	AbstractStation(JavaFXStage window) {
		this.window = window;
		window.setObjectName("AbstractStation");
		// window.init();
	}

	protected JavaFXStage getBaseWindow() {
		return window;
	}

	abstract void start();

	abstract void refreshDisplay();

	public void stop() {
		window.stop();
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshDisplay();
	}

}
