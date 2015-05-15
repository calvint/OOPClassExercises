package workshop4;

import java.util.ArrayList;

public class MeteorStrikeCollection {
	private ArrayList<MeteorStrike> collection = new ArrayList<MeteorStrike>();
	
	public void add(MeteorStrike strike) {
		collection.add(strike);
	}
	
	public void changeBehavior(Behavior newBehavior) {
		for (MeteorStrike strike: collection) {
			strike.setBehavior(newBehavior);
		}
	}
	
	public MeteorStrike get(int index) {
		return collection.get(index);
	}
	
	public void printStrikes() {
		for (int i=1; i<collection.size()-1; i++) {
			collection.get(i).printStrike();
		}
	}
}
