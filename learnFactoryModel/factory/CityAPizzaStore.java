package learnFactoryModel.factory;

import learnFactoryModel.pizza.*;

public class CityAPizzaStore extends PizzaStore {
	
	
	public Pizza createPizza(String style) {
		
		  Pizza pizza = null;
	        if(style.equals("cheese")){
	            pizza = new CityACheesePizza();
	        }else if(style.equals("chicken")){
	            pizza = new CityAChickenPizza();
	        }else if(style.equals("fish")){
	            pizza = new CityAFishPizza();
	        }
	        return pizza;
	}
}
