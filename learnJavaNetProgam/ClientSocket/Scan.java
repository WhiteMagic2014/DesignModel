package learnJavaNetProgam.ClientSocket;

import java.net.Socket;

public class Scan {
	
	public static void main(String args[]){
		String hostname = "localhost";
	
		for (int i = 2048; i < 4096; i++) {
			
			try(Socket socket  = new Socket(hostname,i)){
				
				System.out.println(i+"is available");
				socket.close();
				
			}catch (Exception e) {
//				System.err.println(i+" err");
			}
			
		}
		
	}

}
