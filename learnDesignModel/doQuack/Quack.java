package learnDesignModel.doQuack;

import learnDesignModel.duckBehaviors.QuackBehavior;

/**
 * @author chenhaoyu
 * 发声的具体实现类 呱呱叫
 */
public class Quack implements QuackBehavior{
	@Override
	public void quack() {
		System.out.println("I can speak \"quack! quack! quack!\" ");
	}
}
