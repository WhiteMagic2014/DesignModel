package learnFactoryModel.pizza;

public class FishPizza extends Pizza {
	@Override
	public void initPizza() {

		this.name = "FishPizza";
		this.price = 288;
		this.detail = "FishPizza, 我编不出来了";

	}
}
