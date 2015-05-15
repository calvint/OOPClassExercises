package workshop4;

public class ClassMain {
	public static void main(String[] args) {
		MeteorStrikeCollection collection = new MeteorStrikeCollection();
		collection.add(new MeteorStrike("alabama", "alabama city", "H5", 55F, 2001, 12, 5, 13, 45));
		collection.add(new MeteorStrike("Georgia", "Georgia city", "H5", 45F, 2001, 12, 5, 13, 45));
		collection.add(new MeteorStrike("Florida", "Florida city", "H5", 35F, 2001, 12, 5, 13, 45));
		collection.add(new MeteorStrike("carolina", "carolina city", "H5", 25F, 2001, 12, 5, 13, 45));
		collection.add(new MeteorStrike("sarasota", "sarasota city", "H5", 15F, 2001, 1, 5, 13, 45));
		
		collection.printStrikes();
		collection.changeBehavior(new FranceBehavior());
		collection.get(3).printStrike();
		collection.get(4).printStrike();
		collection.changeBehavior(new GermanyBehavior());
		collection.get(1).printStrike();
		
		
		
	}
}
