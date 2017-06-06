package learnFactoryModel.simpleFactory;

import learnFactoryModel.pizza.*;;

public class SimpleFactory {

	public Pizza createPizza(String style) {
		Pizza pizza = null;

		if (style.equals("white")) {
			pizza = new WhitePizza();
		} else if (style.equals("magic")) {
			pizza = new MagicPizza();
		}

		return pizza;
	}
}
