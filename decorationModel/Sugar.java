package decorationModel;

/**
 * @author chenhaoyu
 * 具体的调料 糖
 */
public class Sugar extends CondimentDecorator{
	Beverage beverage;
	
	public Sugar(Beverage bev) {
		this.beverage = bev;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + ",加糖";
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.5f; //加糖多收5角
	}

}
