package learnDesignModel.doQuack;

import learnDesignModel.duckBehaviors.QuackBehavior;

/**
 * @author chenhaoyu 发声的具体实现类 吱吱叫
 */
public class Squeak implements QuackBehavior {
	@Override
	public void quack() {
		System.out.println("I can speak \"zhi! zhi! zhi! \" ");
	}
}
