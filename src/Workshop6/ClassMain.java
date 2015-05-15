package Workshop6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class ClassMain {
	
	public static void main(String[] args) throws ParseException {
		try {
//			File file = new File(".");
//			for(String fileNames : file.list()) System.out.println(fileNames);
			//parse the certifications file
			BufferedReader reader = new BufferedReader( new FileReader("certifications.txt") );
			ArrayList<Component> applicantList = new ArrayList<Component>();
			int i=0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				i++;
				if (i%2 == 1) {
					applicantList.add(new Applicant(line));
				}
				else {
					String[] tests = line.split("\\s");
					Component applicant = applicantList.get(applicantList.size()-1);
					int j;
					for (j=0; (j < tests.length / 2); j++) {
						
						DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
						Date date = df.parse(tests[2*j+1]);
						GregorianCalendar cal = new GregorianCalendar();
						cal.setTime(date);
						String test = tests[2*j];
						if (test.equals("MCSE")) {
							applicant = new MCSE(cal, applicant);
						}
						if (test.equals("MCSA")) {
							applicant = new MCSA(cal, applicant);
						}
						if (test.equals("MCSD")) {
							applicant = new MCSD(cal, applicant);
						}
						if (test.equals("MTA")) {
							applicant = new MTA(cal, applicant);
						}
					}
					applicantList.set(applicantList.size()-1, applicant);
				}
			}
			//parse the criteria file and make a result file
			GregorianCalendar currentDate = new GregorianCalendar();
			currentDate.set(GregorianCalendar.DATE, 1);
			currentDate.set(GregorianCalendar.MONTH, 9);
			currentDate.set(GregorianCalendar.YEAR, 2014);
			BufferedReader criteriaReader = new BufferedReader( new FileReader("criteria.txt") );
			PrintStream  ps    = new  PrintStream("results.txt");
			ArrayList<String> specs = new ArrayList<String>();
			line = null;
			while ((line = criteriaReader.readLine()) != null) {
				specs.add(line);
			}
			for (String testSpec : specs) {
				String lineToPrint = testSpec;
				String[] details = testSpec.split("\\s");
				if (details[1].equals("0") && details[2].equals("0")) {
					for (Component applicant : applicantList) {
						if (applicant.isCertifiedAfter(details[0], currentDate) != null) {
							lineToPrint = lineToPrint + applicant.getApplicantName();
						}
					}
				}
				if (!(details[1].equals("0")) && details[2].equals("0")) {
					for(Component applicant : applicantList) {
						if (applicant.hasGoodStanding(details[0], currentDate, Integer.parseInt(details[1]))) {
							lineToPrint = lineToPrint + applicant.getApplicantName();
						}
					}
				}
				if (details[1].equals("0") && !(details[2].equals("0"))) {
					GregorianCalendar pastDate = new GregorianCalendar();
					pastDate = (GregorianCalendar) currentDate.clone();
					pastDate.add(GregorianCalendar.YEAR, Integer.parseInt(details[2]));
					for (Component applicant : applicantList) {
						if (applicant.isCertifiedBefore(details[0], pastDate) != null) {
							lineToPrint = lineToPrint + applicant.getApplicantName();
						}
					}
				}
				if (!(details[1].equals("0")) && !(details[2].equals("0"))) {
					GregorianCalendar pastDate = new GregorianCalendar();
					pastDate = (GregorianCalendar) currentDate.clone();
					pastDate.add(GregorianCalendar.YEAR, Integer.parseInt(details[2]));
					for(Component applicant : applicantList) {
						if (applicant.hasGoodStanding(details[0], currentDate, Integer.parseInt(details[1]))
								&& (applicant.isCertifiedBefore(details[0], pastDate) != null)) {
							lineToPrint = lineToPrint + applicant.getApplicantName();
						}
					}
				}
				ps.println(lineToPrint);
			}
			ps.close();
			criteriaReader.close();
			reader.close();
		
		} catch (IOException ioe) {
	        System.out.println(ioe.getMessage());
	        System.exit(1);
	    }
	}
}
