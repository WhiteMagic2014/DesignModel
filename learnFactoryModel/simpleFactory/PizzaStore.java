package learnFactoryModel.simpleFactory;

import learnFactoryModel.pizza.*;

public class PizzaStore {
	SimpleFactory simpleFactory;

	public PizzaStore() {
		simpleFactory = new SimpleFactory();
	}

	public void orderPizza(String style) {
		Pizza pizza = simpleFactory.createPizza(style);
		pizza.initPizza();
		pizza.show();
	}
}
