package learnJavaNetProgam.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DiscardServer {

	public final static int port = 1333;
	public final static int MAX_PACKET_SIZE = 65507; // 65535 减去 ip首部20字节 再减去udp
														// 首部8字节 但是一般来说不能限制在
														// 8192/8k以内

	public static void main(String[] args) {
		byte[] buffer = new byte[MAX_PACKET_SIZE];

		try (DatagramSocket server = new DatagramSocket(port)) {

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			while (true) {
				try {
					server.receive(packet);//receive会阻塞线程
					String s = new String(packet.getData(), 0, packet.getLength(), "8859_1");
					System.out.println(packet.getAddress() + " at port " + packet.getPort() + ".info: " + s);

					packet.setLength(buffer.length);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}
