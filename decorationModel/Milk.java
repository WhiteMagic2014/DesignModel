package decorationModel;

/**
 * @author chenhaoyu
 * 具体的调料 牛奶
 */
public class Milk extends CondimentDecorator{
	Beverage beverage;
	
	/**
	 * @param bev
	 * 获得到一种饮料 将此保存，以便之后做修改（装饰）
	 */
	public Milk(Beverage bev) {
		this.beverage = bev;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + ",加牛奶";
	}

	@Override
	public double cost() {
		return beverage.cost() + 5; //加牛奶多收5块
	}

}
