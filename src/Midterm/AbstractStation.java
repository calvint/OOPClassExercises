package Midterm;

public abstract class AbstractStation {

	protected JavaFXStage  window; 

	AbstractStation(JavaFXStage window) {
		this.window = window;
		window.setObjectName("AbstractStation");
		//window.init();
	}

	protected JavaFXStage getBaseWindow() {
		return window;
	}

	abstract void start();

	abstract void refreshDisplay();
}
