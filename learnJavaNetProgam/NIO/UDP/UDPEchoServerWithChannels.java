package learnJavaNetProgam.NIO.UDP;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPEchoServerWithChannels {
	public final static int port = 1333;
	public final static int MAX_BUFFER_SIZE = 65507;

	public static void main(String[] args) {
		try {
			DatagramChannel channel = DatagramChannel.open();
			DatagramSocket socket = channel.socket();
			InetSocketAddress address = new InetSocketAddress(port);
			socket.bind(address);
			ByteBuffer buffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);
			while (true) {
				SocketAddress client = channel.receive(buffer);
				buffer.flip();
				channel.send(buffer, client);
				buffer.clear();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
