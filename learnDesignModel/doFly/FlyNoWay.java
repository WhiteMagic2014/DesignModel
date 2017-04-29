package learnDesignModel.doFly;

import learnDesignModel.duckBehaviors.FlyBehavior;

/**
 * @author chenhaoyu
 * 飞行的具体实现类，表现为 不会飞
 */
public class FlyNoWay implements FlyBehavior {
	@Override
	public void fly() {
		System.out.println("I can't fly");
	}
}
