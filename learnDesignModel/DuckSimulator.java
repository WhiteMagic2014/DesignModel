package learnDesignModel;

import learnDesignModel.doFly.FlyRocket;
import learnDesignModel.ducks.Duck;
import learnDesignModel.ducks.MallardDuck;
import learnDesignModel.ducks.ModelDuck;

public class DuckSimulator {
	public static void main(String[] args) {

//		Duck mallard = new MallardDuck();
//		mallard.display();
//		mallard.performFly();
//		mallard.performQuack();
		
//		Duck mode = new ModelDuck();
//		mode.display();
//		mode.performFly();
//		mode.performQuack();
//		
//		mode.setFlyBehavior(new FlyRocket());
//		mode.performFly();

		DuckCall duckCall = new DuckCall();
		duckCall.call();
		
	}
}
