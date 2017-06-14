package learnFactoryModel.pizza;

public class CityAChickenPizza extends ChickenPizza {
	@Override
	public void initPizza() {
		super.initPizza();
		this.detail = "A特产" + this.detail;
	}
}
