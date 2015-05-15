package workshop4;

public class FranceBehavior implements Behavior {
	
	public String printDate(String day, String month, String year) {
		return day + "/" + month  + "/" + year;
	}
	
	public String printWeight(float weight) {
		return new MyFloat(weight, new EuropePrint()).prettyString() + "kg";
	}
	
	public String printTime(int hour, int minute) {
		return Integer.toString(hour) + ":" + Integer.toString(minute);
	}
}

