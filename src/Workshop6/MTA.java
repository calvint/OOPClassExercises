package Workshop6;

import java.util.GregorianCalendar;

public class MTA extends Test {
	public MTA(GregorianCalendar date, Component parent) {
		this.name = "MTA";
		this.recertificationPeriod = 0;
		this.parent = parent;
		this.date = date;
	}
}