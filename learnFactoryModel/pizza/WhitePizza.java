package learnFactoryModel.pizza;

public class WhitePizza extends Pizza {
	@Override
	public void initPizza() {
		this.name = "white special pizza!";
		this.price = 518;
		this.detail = "pizza 限定白款";
	}
}
