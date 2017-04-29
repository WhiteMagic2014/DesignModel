package learnDesignModel.doFly;

import learnDesignModel.duckBehaviors.FlyBehavior;

/**
 * @author chenhaoyu
 * 飞行的具体实现类，表现为 使用翅膀飞
 */
public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I'm fly with wings");
	}
	
}
