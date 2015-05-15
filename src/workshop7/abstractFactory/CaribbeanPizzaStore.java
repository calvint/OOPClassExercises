package workshop7.abstractFactory;

public class CaribbeanPizzaStore extends PizzaStore {

	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory = 
			new CaribbeanPizzaIngredientFactory();
 
		if (item.equals("cheese")) {
  
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("Caribbean Style Cheese Pizza");
  
		} else if (item.equals("veggie")) {
 
			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("Caribbean Style Veggie Pizza");
 
		} else if (item.equals("clam")) {
 
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("Caribbean Style Clam Pizza");
 
		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("Caribbean Style Pepperoni Pizza");
 
		} else if (item.equals("cocoanut")) {

			pizza = new CocoanutPizza(ingredientFactory);
			pizza.setName("Caribbean Style Cocoanut Pizza");
 
		} 
		
		return pizza;
	}

}
