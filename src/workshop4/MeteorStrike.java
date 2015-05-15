package workshop4;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MeteorStrike {
	private String name;
	private String location;
	private String type;
	private float weight;
	private GregorianCalendar date;
	private Behavior behavior;
	
	public MeteorStrike(String name, String location, String type, float weight, int year, int month, int day, int hour, int minute) {
		this.behavior = new USBehavior();
		this.name = name;
		this.location = location;
		this.type = type;
		this.weight = weight;
		
		date = new GregorianCalendar();
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month -1);
		date.set(Calendar.DAY_OF_MONTH, day);
		date.set(Calendar.HOUR_OF_DAY, hour);
		date.set(Calendar.MINUTE, minute);
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getType() {
		return type;
	}

	public float getWeight() {
		return weight;
	}

	public GregorianCalendar getDate() {
		return date;
	}
	
	public int get(int calendarInt) {
		int[] list = {1,2,5,11,12};
		int result = 0;
		for (int refInt: list) {
			if (calendarInt == refInt) {
				result = date.get(calendarInt);
				if (refInt == 5) {
					result ++;
				}
			}
		}
		return result;
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}
	
	public void printDate() {
		System.out.println(this.behavior.printDate(Integer.toString(this.get(Calendar.DAY_OF_MONTH)), 
				Integer.toString(this.get(Calendar.MONTH)), Integer.toString(this.get(Calendar.YEAR))));
	}
	
	public void printWeight() {
		System.out.println(this.behavior.printWeight(weight));
	}
	
	public void printTime() {
		System.out.println(this.behavior.printTime(  this.get(Calendar.HOUR_OF_DAY), this.get(Calendar.MINUTE)));
	}
	
	public void printStrike() {
		this.printDate();
		this.printTime();
		this.printWeight();
	}
}
