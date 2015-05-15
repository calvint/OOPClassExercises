package Workshop6;

import java.util.GregorianCalendar;

public class Applicant implements Component {
	String name;
	
	public Applicant(String name) {
		this.name = name;
	}
	
	public String getApplicantName() {
		return this.name;
	}

	public GregorianCalendar isCertifiedBefore(String exam, GregorianCalendar date) {
		return null;
	}

	public GregorianCalendar isCertifiedAfter(String test, GregorianCalendar date) {
		return null;
	}

	public GregorianCalendar isCertifiedInRange(String name, GregorianCalendar before) {
		return null;
	}

	public Boolean hasGoodStanding(String testName,
			GregorianCalendar initialDate, int years) {
		return false;
	}
}
