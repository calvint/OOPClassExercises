package workshop7.abstractFactory;

public class CocoanutPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
	
	public CocoanutPizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}

	@Override
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cocoanut = ingredientFactory.createCocoanut();
	}

}
