package decorationModel;

public class CoffeeSimulator {

	public static void main(String[] args) {

		Beverage origin = new BlackCoffee();
		System.out.println(origin.getDescription()+" : " + origin.cost()+"元\n");
		
		origin = new Milk(origin);
		System.out.println(origin.getDescription()+" : " + origin.cost()+"元\n");

		origin = new Sugar(origin);
		System.out.println(origin.getDescription()+" : " + origin.cost()+"元\n");

		
	}

}
