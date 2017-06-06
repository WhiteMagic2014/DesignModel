package learnFactoryModel.factory_1;

import learnFactoryModel.pizza.*;

/**
 * @author chenhaoyu 这里把店做成抽象类 比如
 *         有不同地方的分店，有特色产品（新的pizza类），或者对pizza有新的操作，可以通过工厂做，也可以随便他怎么做
 *         在这里，我有一个大胆的想法。。 工厂factory中使用装饰着模式，用工厂去管理装饰类这一堆对象，然后根据style来组装对象，最后返回
 */
public abstract class PizzaStore {

	public void orderPizza(String style) {
		Pizza pizza = createPizza(style);

		pizza.initPizza();
		pizza.show();
	}

	/**
	 * @param style
	 * @return Pizza 现在 实例化对象的工作被分散到了这个方法中，这个方法就相当于是一个工厂
	 */
	public abstract Pizza createPizza(String style);

}
