package workshop7.MethodPizzaFactory;

public class CaribbeanStylePizzaStore extends PizzaStore {

	Pizza createPizza(String item) {
		if (item.equals("cocoanut")) {
			return new CaribbeanStyleCocoanutPizza();
		}  else return null;
	}

}
