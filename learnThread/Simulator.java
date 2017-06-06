package learnThread;

public class Simulator {

	public static void main(String[] args) {
//		//cat 继承thread来实现
//		Cat cat = new Cat();
//		cat.start();
//		
//		//dog 通过runnable接口来实现
//		Dog dog = new Dog();
//		Thread thread = new Thread(dog);
//		thread.start();
		
		Sale sale =new Sale();
		
		Thread t1 = new Thread(sale);
		Thread t2 = new Thread(sale);
		Thread t3 = new Thread(sale);
		Thread t4 = new Thread(sale);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}

}
