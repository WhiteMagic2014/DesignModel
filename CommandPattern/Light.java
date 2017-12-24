package CommandPattern;

/**
 * @author chenhaoyu 一个具体要操作中的类，这个类是可变的，由厂家所提供的，作为遥控器我不需要知道他到底是什么，只需要知道让他工作就好
 */
public class Light {
	public void on() {
		System.out.println("开灯");
	}

	public void off() {
		System.out.println("关灯");
	}
}
