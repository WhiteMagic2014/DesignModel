package learnJavaNetProgam.NIO;

public class rotationtest {

	public static void main(String[] args) {

		byte[] rotation = new byte[95 * 2];
		for (byte i = ' '; i <= '~'; i++) {
			rotation[i - ' '] = i;
			rotation[i + 95 - ' '] = i;
		}
		
		for(byte i : rotation){
			System.out.print((char)i);
		}
		
		
	}

}
