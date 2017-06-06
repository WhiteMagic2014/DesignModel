package learnFactoryModel.pizza;

public abstract class Pizza {
	public String name;
	public int price;
	public String detail;


	
	public abstract void initPizza();

	public void show() {
		System.out.println("name:" + name + "\nprice:" + price + "\ndetail:" + detail);
	}

}
