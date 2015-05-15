package workshop7.abstractFactory;

public class CaribbeanPizzaIngredientFactory implements PizzaIngredientFactory {

	public Dough createDough() {
		return new ThinCrustDough();
	}

	public Sauce createSauce() {
		return new CaribbeanSauce();
	}

	public Cheese createCheese() {
		return new CaribbeanCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Mushroom()};
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}

	public Cocoanut createCocoanut() {
		return new SweetenedCocoanut();
	}
}
