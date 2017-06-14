package learnFactoryModel.factory;

public class Simulator {

	public static void main(String[] args) {
		PizzaStore aStore = new CityAPizzaStore();
		aStore.orderPizza("cheese");
	}

}
