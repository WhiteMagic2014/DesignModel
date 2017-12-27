package learnJavaNetProgam.NIO.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;


public class UDPEchoCilentWithChannels {

	public final static int port = 1333;
	public final static int Limit = 100;

	public static void main(String[] args) {

		SocketAddress remote;
		try {
			remote = new InetSocketAddress(args[0], port);
		} catch (RuntimeException e) {
			System.err.println("uasge: java UDPEchoCilentWithChannels host ");
			return;
		}

		try (DatagramChannel channel = DatagramChannel.open()) {
			channel.configureBlocking(false);
			channel.connect(remote);

			Selector selector = Selector.open();
			channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

			ByteBuffer buffer = ByteBuffer.allocate(4);
			int n = 0;
			int numbersRead = 0;

			while (true) {
				if (numbersRead == Limit) {
					break;
				}
				selector.select(60000);
				Set<SelectionKey> readyKeys = selector.selectedKeys();

				if (readyKeys.isEmpty() && n == Limit) {
					break;
				} else {
					Iterator<SelectionKey> iterator = readyKeys.iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						iterator.remove();

						if (key.isReadable()) {
							buffer.clear();
							channel.read(buffer);
							buffer.flip();
							int echo = buffer.getInt();
							System.out.println("read " + echo);
							numbersRead++;
						}

						if (key.isWritable()) {
							buffer.clear();
							buffer.putInt(n);
							buffer.flip();
							channel.write(buffer);
							System.out.println("write " + n);
							n++;
							if (n == Limit) {
								key.interestOps(SelectionKey.OP_READ);
							}

						}

					}

				}

			}

			System.out.println("echoed " + numbersRead + " out of " + Limit + " sent");
			System.out.println("successed rate " + 100.0 * numbersRead / Limit + "%");

		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
