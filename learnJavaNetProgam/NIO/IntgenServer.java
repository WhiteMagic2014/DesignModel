package learnJavaNetProgam.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class IntgenServer {

	public static int DEFAULT_PORT = 1333;

	public static void main(String[] args) {

		int port = DEFAULT_PORT;

		System.out.println("listening for conntecions on port " + port);

		ServerSocketChannel serverChannel;
		Selector selector;

		try {
			serverChannel = ServerSocketChannel.open();
			ServerSocket ss = serverChannel.socket();
			InetSocketAddress address = new InetSocketAddress(port);
			ss.bind(address);
			serverChannel.configureBlocking(false);
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("创建ServerSocketChannel失败");
			return;
		}

		while (true) {
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

			Set<SelectionKey> readykeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readykeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();

				try {

					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel client = server.accept();
						System.out.println("accept connection from " + client);
						client.configureBlocking(false);
						SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
						ByteBuffer output = ByteBuffer.allocate(4);
						output.putInt(0);
						output.flip();
						key2.attach(output);
					} else if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer output = (ByteBuffer) key.attachment();

						if (!output.hasRemaining()) {
							output.rewind();
							int value = output.getInt();
							output.clear();
							output.putInt(value + 1);
							output.flip();
						}

						client.write(output);
					}

				} catch (IOException e) {
					key.cancel();
					try {
						key.channel().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}

		}
	}
}
