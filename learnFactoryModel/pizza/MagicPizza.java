package learnFactoryModel.pizza;

public class MagicPizza extends Pizza {

	@Override
	public void initPizza() {
		this.name = "magic special pizza!";
		this.price = 288;
		this.detail = "super magic pizza, 我编不出来了";
	}

}
