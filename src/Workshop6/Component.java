package Workshop6;

import java.util.GregorianCalendar;

public interface Component {
	public GregorianCalendar isCertifiedBefore(String testName, GregorianCalendar date);
	
	public GregorianCalendar isCertifiedAfter(String testName, GregorianCalendar date);
	
	public GregorianCalendar isCertifiedInRange(String testName, GregorianCalendar before);
	
	public Boolean hasGoodStanding(String testName, GregorianCalendar initialDate, int years);
	
	public String getApplicantName();
}
