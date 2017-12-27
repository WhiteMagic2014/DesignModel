package learnJavaNetProgam.NIO.UDP;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPDiscardServerWithChannels {

	public final static int port = 1333;
	
	public final static int MAX_PACKET_SIZE = 65507;
	
	
	public static void main(String[] args){
		try {
			
			DatagramChannel channel = DatagramChannel.open();
			DatagramSocket socket = channel.socket();
			InetSocketAddress address = new InetSocketAddress(port);
			socket.bind(address);
			
			ByteBuffer buffer = ByteBuffer.allocate(MAX_PACKET_SIZE);
			
			while(true){
				SocketAddress client = channel.receive(buffer);
				buffer.flip();
				System.out.print(client +" says: ");
				while(buffer.hasRemaining()){
					System.out.print((char)buffer.get());
				}
				System.out.println();
				buffer.clear();
			}
			
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	
}
