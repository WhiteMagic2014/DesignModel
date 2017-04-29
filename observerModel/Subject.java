package observerModel;

/**
 * @author chenhaoyu 发布者接口
 */
public interface Subject {

	/**
	 * @param observer
	 *            将observer注册进推送名单
	 */
	public void registerObserver(Observer observer);

	/**
	 * @param observer
	 *            将observer从推送名单中移除
	 */
	public void removeObserver(Observer observer);

	/**
	 * 给所有在名单内的observer执行推送
	 */
	public void notifyObservers();
}
