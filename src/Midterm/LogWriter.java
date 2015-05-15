package Midterm;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogWriter {
	String logFileLocation;

	public LogWriter(String logFileLocation) {
		this.logFileLocation = logFileLocation;
	}
	
	public void writeToLog(String string) {
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFileLocation, true), "UTF-8"));
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
			string = sdf.format(new Date()).toString() + " : " + string + "\r\n";
			writer.append(string);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
