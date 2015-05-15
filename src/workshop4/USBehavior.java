package workshop4;

public class USBehavior implements Behavior {
	
	public String printDate(String day, String month, String year) {
		return month + "/" + day + "/" + year;
	}
	
	public String printWeight(float weight) {
		return new MyFloat( (float) (weight *  2.20462), new USPrint()).prettyString() + "lbs";
	}
	
	public String printTime(int hour, int minute) {
		String timeString = "am";
		if (hour > 12) {
			hour = hour - 12;
			timeString = "pm";
		}
		return Integer.toString(hour) + ":" + Integer.toString(minute) + timeString;
	}
}
