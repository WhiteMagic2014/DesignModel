package learnDesignModel.doFly;

import learnDesignModel.duckBehaviors.FlyBehavior;

/**
 * @author chenhaoyu
 * 飞行的具体实现类，表现为 使用火箭日天
 */
public class FlyRocket implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println("5，4，3，2，1———— Rocket！");
	}

}
