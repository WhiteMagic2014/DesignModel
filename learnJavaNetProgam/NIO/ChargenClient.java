package learnJavaNetProgam.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 一个使用nio的客户端，实际上没什么用 nio一般用于服务器
 * @author chenhaoyu
 *
 */
public class ChargenClient {

	public static int DEFAULT_PORT = 1333;

	public static void main(String[] args) {

		String host = "www.bilibili.com";

		try {

			SocketAddress address = new InetSocketAddress(host, DEFAULT_PORT);
			SocketChannel client = SocketChannel.open(address);

			ByteBuffer buffer = ByteBuffer.allocate(74);
			WritableByteChannel out = Channels.newChannel(System.out);//将输出 改到sout

			while (client.read(buffer) != -1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
