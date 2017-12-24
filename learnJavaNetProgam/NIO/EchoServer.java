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

public class EchoServer {

	public static int DEFAULT_PORT = 1333;

	public static void main(String[] args) {
		int port;

		try {
			port = Integer.parseInt(args[0]);
		} catch (RuntimeException e) {
			port = DEFAULT_PORT;
		}

		System.out.println("listening for connections on port " + port);

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

						System.out.println("accepted connection from " + client);
						client.configureBlocking(false);
						SelectionKey clientKey = client.register(selector,
								SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						ByteBuffer buffer = ByteBuffer.allocate(100);
						clientKey.attach(buffer);

					} else if (key.isReadable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer out = (ByteBuffer) key.attachment();
						client.read(out);

					} else if (key.isWritable()) {
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer out = (ByteBuffer) key.attachment();
						out.flip();
						client.write(out);
						out.compact();
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
