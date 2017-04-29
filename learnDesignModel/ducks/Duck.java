package learnDesignModel.ducks;

import learnDesignModel.duckBehaviors.FlyBehavior;
import learnDesignModel.duckBehaviors.QuackBehavior;

/**
 * @author chenhaoyu
 * 鸭子超类
 */
public abstract class Duck {
	
//	为行为接口类型声明两个引用变量，所有鸭子子类都将继承他们
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	

	public Duck() {
	}
	
	public abstract void display();
	
	public void performFly() {
		flyBehavior.fly();
	}
	
	public void performQuack() {
		quackBehavior.quack();
	}
	
	public void setFlyBehavior(FlyBehavior fb) {
		this.flyBehavior = fb;
	}
	
	public void setQuackBehavior(QuackBehavior qb ) {
		this.quackBehavior =qb;
	}
	
	
	
	public void swim() {
		System.out.println("所有鸭子都会游泳");
	}

}
