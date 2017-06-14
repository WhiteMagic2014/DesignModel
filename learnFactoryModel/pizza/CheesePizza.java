package learnFactoryModel.pizza;

public class CheesePizza extends Pizza {
	@Override
	public void initPizza() {
		this.name = "CheesePizza";
		this.price = 288;
		this.detail = "CheesePizza, 我编不出来了";
	}
}
