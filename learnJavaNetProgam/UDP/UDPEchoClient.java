package learnJavaNetProgam.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {

	public final static int PORT = 1333;

	public static void main(String[] args) {

		String hostname = "localhost";
		if (args.length > 0) {
			hostname = args[0];
		}

		try {
			InetAddress address = InetAddress.getByName(hostname);
			DatagramSocket socket = new DatagramSocket();

			SenderThread sender = new SenderThread(socket, address, PORT);
			sender.start();
			ReceiverThread receiver = new ReceiverThread(socket);
			receiver.start();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}

class SenderThread extends Thread {

	private InetAddress address;
	private DatagramSocket socket;
	private int port;
	private volatile boolean stopped = false;

	public SenderThread(DatagramSocket socket, InetAddress address, int port) {

		this.socket = socket;
		this.address = address;
		this.port = port;
		this.socket.connect(address, port);//udp的connect不是真正意义上的连接 而是只接受这个地址/端口的 udp数据报的一个筛选
	}

	public void halt() {
		this.stopped = true;
	}

	@Override
	public void run() {
		try {
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				if (stopped) {
					return;
				}
				String theLine = userInput.readLine();
				if (theLine.equals(".")) {
					break;
				}
				byte[] data = theLine.getBytes("UTF-8");
				DatagramPacket output = new DatagramPacket(data, data.length, address, port);
				socket.send(output);
				Thread.yield();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

class ReceiverThread extends Thread {
	
	private DatagramSocket socket;
	private volatile boolean stopped = false;
	
	public ReceiverThread(DatagramSocket socket) {
		this.socket =socket;
		
	}

	public void halt(){
		this.stopped = true;
	}
	
	@Override
	public void run() {
		byte[] buffer = new byte[65507];
		while(true){
			
			if (stopped) {
				return;
			}
			
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			
			try{
				socket.receive(dp);
				String string = new String(dp.getData(),0,dp.getLength(),"UTF-8");
				System.out.println(string);
				Thread.yield();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	
}









