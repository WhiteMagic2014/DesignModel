package decorationModel;

/**
 * @author chenhaoyu
 * 一种具体的饮料 
 */
public class BlackCoffee extends Beverage{
	
	public BlackCoffee() {
		description = "一杯清咖";
	}

	@Override
	public double cost() {
		return 18;//一杯18块
	}

}
