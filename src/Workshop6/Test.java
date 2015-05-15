package Workshop6;

import java.util.GregorianCalendar;

public abstract class Test implements Component{
	String name;
	GregorianCalendar date;
	int recertificationPeriod;
	Component parent;

	public GregorianCalendar isCertifiedBefore(String testName, GregorianCalendar date) {
		if (this.name.equals(testName) && (this.date.compareTo(date) == -1)) {
			return this.date;
		}
		else {
			return this.parent.isCertifiedBefore(testName, date);
		}
	}

	public GregorianCalendar isCertifiedAfter(String testName, GregorianCalendar date) {
		if (this.name == testName && (this.date.compareTo(date) == 1)) {
			return this.date;
		}
		else {
			return this.parent.isCertifiedBefore(testName, date);
		}
	}

	public GregorianCalendar isCertifiedInRange(String testName, GregorianCalendar before) {
		if (this.name == testName) {
			if (this.date.compareTo(before) == 1) {
				return this.date;
			}
			else {
				GregorianCalendar lastDate = parent.isCertifiedInRange(testName, before);
				if (lastDate == null) {
					return null;
				}
				lastDate.add(GregorianCalendar.YEAR, this.recertificationPeriod);
				if ( this.date.compareTo(lastDate) == 1){
					return this.date;
				}
				lastDate.add(GregorianCalendar.YEAR, (-1 * this.recertificationPeriod));
				return lastDate;
			}
		}
		else {
			return parent.isCertifiedInRange(testName, before);
		}
	}

	public Boolean hasGoodStanding(String testName, GregorianCalendar initialDate, int years) {
		if (this.name == testName) {
			initialDate.add(GregorianCalendar.YEAR, (-1 * this.recertificationPeriod) );
			if (this.date.compareTo(initialDate) == 1) {
				return false;
			}
			else {
				initialDate.add(GregorianCalendar.YEAR, this.recertificationPeriod);
				initialDate.add(GregorianCalendar.YEAR, years);
				GregorianCalendar lastDate = parent.isCertifiedInRange(testName, initialDate);
				if (lastDate == null) {
					return false;
				}
				lastDate.add(GregorianCalendar.YEAR, this.recertificationPeriod);
				if (this.date.compareTo(lastDate) == -1) {
					return false;
				}
				lastDate.add(GregorianCalendar.YEAR, (-1 * this.recertificationPeriod));
				return true;
			}
		}
		return parent.hasGoodStanding(testName, initialDate, years);
	}
	
	public String getApplicantName() {
		return parent.getApplicantName();
	}
}
