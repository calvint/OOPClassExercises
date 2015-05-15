package workshop7.MethodPizzaFactory;


public class PizzaTestDrive {
 
	public static void main(String[] args) {
		PizzaStore caribbeanStore = new CaribbeanStylePizzaStore();
		
		Pizza pizza = caribbeanStore.orderPizza("cocoanut");
		System.out.println("Joel ordered a " + pizza.getName() + "\n");
	}
}
