package learnDesignModel;

import learnDesignModel.doQuack.Quack;
import learnDesignModel.duckBehaviors.QuackBehavior;

public class DuckCall {
	private QuackBehavior quackBehavior;
	
	public DuckCall(){
		quackBehavior = new Quack();
	}
	
	public void call() {
		quackBehavior.quack();
	}
}
