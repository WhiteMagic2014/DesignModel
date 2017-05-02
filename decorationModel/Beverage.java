package decorationModel;

/**
 * @author chenhaoyu 超类 饮料类
 */
public abstract class Beverage {
	String description = "未知饮料";

	public String getDescription() {
		return description;
	}
	
	/**
	 * @return 饮料的价钱
	 */
	public abstract double cost();

}
