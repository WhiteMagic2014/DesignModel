package learnFactoryModel.pizza;

public class CityAFishPizza extends FishPizza {
	@Override
	public void initPizza() {
		super.initPizza();
		this.detail = "A特产" + this.detail;
	}
}
