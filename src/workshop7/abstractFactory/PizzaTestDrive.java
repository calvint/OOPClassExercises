package workshop7.abstractFactory;


public class PizzaTestDrive {
 
	public static void main(String[] args) {
		PizzaStore CocoStore = new CaribbeanPizzaStore();
 
		Pizza pizza = CocoStore.orderPizza("cocoanut");
		System.out.println("Ethan ordered a " + pizza + "\n");
	}
}
