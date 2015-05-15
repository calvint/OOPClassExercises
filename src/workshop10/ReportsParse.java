package workshop10;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class ReportsParse {

	static Manager reportsParse( BufferedReader reader) {

		String employeeRecord;
		try {
			HashMap<Integer, Manager> managerMap = new HashMap<Integer, Manager>();
			Manager rootManager = null;
			while ( (employeeRecord=reader.readLine()) != null ) {

				Scanner in = new Scanner(employeeRecord);
				in.useDelimiter("\\s+");
				int    recordTag;
				int    reportTag;
				String employeeType;
				String employeeName;
				recordTag      = in.nextInt();
				reportTag      = in.nextInt();
				employeeType   = in.next();
				employeeName   = in.nextLine();
				in.close();
				
				
				
				// TODO: insert into a structure
				// The structure should have multiple collections of 
				//        EmployeeRecord(recordTag,reportTag,employeeType,employeeName)
				//   - collection 1, use TreeMap or HashMap to store <recordTag,employeeRecord>
				//   - collection 2, create a CompositePattern report tree based on the information
				// The add() method that you will call here should use (collection 1) look up to find 
				//     to find where to insert into (collection 2)
				
				if (employeeType.equals("Manager")) {
					Manager newManager = new Manager(employeeName);
//					add to map of managers
					managerMap.put(recordTag, newManager);
					if (rootManager == null) {
						rootManager = newManager;
					} else {
//						add to composite structure
						Manager newManagerzBoss = managerMap.get(reportTag);
						newManagerzBoss.add(newManager);
					}
				} else {
					Employee newEmployee = new Employee(employeeName);
					Manager newEmployeezBoss = managerMap.get(reportTag);
					newEmployeezBoss.add(newEmployee);
				}
				
				
			}
			return rootManager;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
