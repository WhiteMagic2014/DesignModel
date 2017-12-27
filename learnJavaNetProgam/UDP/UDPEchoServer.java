package learnJavaNetProgam.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServer {

	public final static int DEFAULT_PORT = 1333;

	public static void main(String[] args) {
		UDPServer server = new UDPEchoServer();
		Thread t = new Thread(server);
		t.start();
	}

	public UDPEchoServer() {
		super(DEFAULT_PORT);
	}

	@Override
	public void respond(DatagramSocket socket, DatagramPacket request) throws IOException {
		System.out.println("收到：" + new String(request.getData(),"UTF-8"));
		DatagramPacket outgoing = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),request.getPort());
		socket.send(outgoing);
	}

}
