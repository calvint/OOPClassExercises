package workshop8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {
	private static Logger onlyLogger = null;
	private ArrayList<String> entries;
	private int oldRecordCount;
	
	
	public Logger() {
//		popuate the array with the log file
		entries = new ArrayList<String>();
		try {
			//make new buffered reader of logger.txt
			BufferedReader reader = new BufferedReader( new FileReader("logger.txt") );
			oldRecordCount = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				entries.add(line);
				oldRecordCount++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized Logger getInstance() {
		if (onlyLogger == null) {
			onlyLogger = new Logger();
		}
		return onlyLogger;
	}
	
	public synchronized void addRecord(String record) {
		//add string to array list
		entries.add(record);
	}
	
	public synchronized String getRecord(int recordNum) {
		//return string of that index
		return entries.get(recordNum);
	}
	
	public synchronized void close() {
		//write all new strings to log file
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt", true));  
			for(int i = oldRecordCount; i<entries.size(); i++) {
				writer.newLine();
				writer.write(entries.get(i));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
