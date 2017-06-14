package learnFactoryModel.pizza;

public class ChickenPizza extends Pizza {
	@Override
	public void initPizza() {
		this.name = "ChickenPizza";
		this.price = 288;
		this.detail = "ChickenPizza, 我编不出来了";
	}
}
