package learnJavaNetProgam.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DayTimeUDPClient {
	private static final int port = 1333;
	private static final String hostname = "localhost";
	

	public static void main(String[] args){
		
		try(DatagramSocket socket = new DatagramSocket(0)){
			socket.setSoTimeout(10000);
			InetAddress host = InetAddress.getByName(hostname);
			DatagramPacket request = new DatagramPacket(new byte[1],1,host,port);
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			socket.send(request);
			socket.receive(response);
			
			String result = new String(response.getData(),0,response.getLength(),"US-ASCII");
			
			System.out.println(result);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
