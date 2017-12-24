package learnJavaNetProgam.ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;

public class Scanner {

	public static void main(String[] args) {

		for (int i = 1025; i <= 2000; i++) {
			
			try {
				ServerSocket server = new ServerSocket(i);
			} catch (IOException e) {
					System.out.println("a port on " + i);
			}
			
		}
		
	}

}
