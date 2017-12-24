import java.util.Random;

public class Special {

	public static void main(String[] args) {

		times(5);

	}

	public static void times(int n) {
		for (int i = 1; i <= n; i++) {
			System.out.println("第" + i + "注");
			System.out.print("红：");
			red();
			System.out.print("蓝：");
			bule();
		}
	}

	public static void red() {
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			System.out.print(" " + (random.nextInt(34) + 1));
		}
		System.out.println();
	}

	public static void bule() {
		Random random = new Random();
		for (int i = 0; i < 1; i++) {
			System.out.print(" " + (random.nextInt(34) + 1));
		}
		System.out.println("\n");
	}

}
