package learnFactoryModel.abstractFactory;

public class CityAPizzaIngredientFactory implements PizzaIngredientFactory {
	public Dough createDough() {
		return new CityADough();
	}

	public Sauce createSause() {
		return new CityASause();
	}

	public Cheese createCheese() {
		return new CityACheese();
	}

	public Meat createMeat() {
		return new CityAMeat();
	}

	public Veggies[] createVeggies() {
		Veggies CityAVeggies[] = null;
		// Veggies CityAVeggies[] = { 各种cityA城市洗好的蔬菜 };
		return CityAVeggies;
	}

	public Fish createFish() {
		return new CityAFish();
	}
}
