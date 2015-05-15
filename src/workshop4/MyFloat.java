package workshop4;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MyFloat {
	double myFloat;
	BehaviorPrint printBehaviorInstance;
	
	MyFloat(double number, BehaviorPrint behavior) {
		printBehaviorInstance = behavior;
		myFloat = number;
	}
	
	void setPrintBehavior(BehaviorPrint behavior) {
		printBehaviorInstance = behavior;
	}

	String prettyString() {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator( printBehaviorInstance.decimal()  );
		otherSymbols.setGroupingSeparator(printBehaviorInstance.separator()); 
		DecimalFormat myFormater = new DecimalFormat("###,###.###", otherSymbols);
		return myFormater.format(myFloat);
	}
}

