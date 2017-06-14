package learnFactoryModel.pizza;

public class CityACheesePizza extends CheesePizza {
	@Override
	public void initPizza() {
		super.initPizza();
		this.detail = "A特产" + this.detail;
	}
}
