package learnDesignModel.doQuack;

import learnDesignModel.duckBehaviors.QuackBehavior;

/**
 * @author chenhaoyu 发声的具体实现类 不会叫
 */
public class MuteQuack implements QuackBehavior {
	@Override
	public void quack() {
		System.out.println("SCLIENCE————————");
	}
}
