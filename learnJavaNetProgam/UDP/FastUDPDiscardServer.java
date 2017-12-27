package learnJavaNetProgam.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FastUDPDiscardServer extends UDPServer {

	public final static int DEFAULT_PORT = 1333;

	public FastUDPDiscardServer() {
		super(DEFAULT_PORT);
	}

	public static void main(String[] args) {
		UDPServer server = new FastUDPDiscardServer();
		Thread t = new Thread(server);
		t.start();
	}

	@Override
	public void respond(DatagramSocket socket, DatagramPacket request) throws IOException {
	}

}
