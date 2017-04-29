package observerModel;

/**
 * @author chenhaoyu 观察者接口
 */
public interface Observer {
	/**
	 * @param info1
	 * @param info2
	 *            Subject的实现类通过update来给订阅者传递数据
	 */
	public void update(String info1, String info2);

	
	/**
	 * @param sub
	 * 给观察者一个subject对象
	 * 说明该observer开始订阅subject对象
	 */
	public void receive(Subject sub);

	/**
	 * 观察者主动取消订阅
	 */
	public void notReceive();
}
