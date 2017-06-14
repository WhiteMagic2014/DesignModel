package learnFactoryModel.abstractFactory;

public interface PizzaIngredientFactory {
	public Dough createDough();//面团
    public Sauce createSause();//酱料
    public Cheese createCheese();//芝士
    public Meat createMeat();//肉
    public Veggies[] createVeggies();//蔬菜
    public Fish createFish();//鱼
}
