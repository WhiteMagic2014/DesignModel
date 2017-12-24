
public class jieceng {
	public static void main(String args[]) {

		System.out.println(getjieceng(4));

	}

	private static int getjieceng(int i) {
		if (i > 1) {
			return i * getjieceng(i-1);
		} else {
			return 1;
		}
	}

}
