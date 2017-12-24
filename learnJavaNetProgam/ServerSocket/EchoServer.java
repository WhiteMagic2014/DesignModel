package learnJavaNetProgam.ServerSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import learnJavaNetProgam.AsciiPic;

/**
 * 使用了nio
 * 
 * @author chenhaoyu
 *
 */
public class EchoServer {

	public final static int DEFAULT_PORT = 1333;
	public final static Logger auditLogger = Logger.getLogger("requests");
	public final static Logger errorLogger = Logger.getLogger("errors");

	public static void main(String[] args) {
		int port;

		try {
			port = Integer.parseInt(args[0]);
		} catch (RuntimeException e) {
			port = DEFAULT_PORT;
		}
		
		ByteBuffer picBuffer = ByteBuffer.wrap(AsciiPic.createAsciiPic("/Users/chenhaoyu/Desktop/fgo/bg.jpg"));

		
		System.out.println("Listening for connetions on port " + port);

		// ServerSocketChannel serverChannel;
		Selector selector;

		try {
			// channel的创建

			ServerSocketChannel serverChannel = ServerSocketChannel.open();// 打开ServerSocketChannel
			// ServerSocket ss = serverChannel.socket();// 创建ServerSocket
			// InetSocketAddress adderss = new InetSocketAddress(port);// 创建地址
			// ss.bind(adderss);// 绑定

			// 上面3行可以如下写在一起
			// serverChannel.socket().bind(new InetSocketAddress(port));
			// 也可以直接绑定 （java7以后）
			serverChannel.bind(new InetSocketAddress(port));

			// channel与selector的绑定
			selector = Selector.open();// 初始化selector
			serverChannel.configureBlocking(false);// 只有非阻塞的channel才能和selector一起使用
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);// 将channel注册到selector
			// register这个方法其实返回了一个selectedKeys的set
			// 这里没用set将其保存，所以再下面使用了selector.selectedKeys()来获得这个set
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				selector.select();// 使用select方法来选择通道，返回你所感兴趣的事件（如连接、接受、读或写）已经准备就绪的那些通道
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readyKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();

				try {
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						// 下面就是在上面提到过的一个 channel 与selector注册绑定的过程
						SocketChannel client = server.accept();
						Date now = new Date();
						auditLogger.info(now + "Accepted connection from " + client);

						client.configureBlocking(false);
						SelectionKey clientKey = client.register(selector,
								SelectionKey.OP_WRITE | SelectionKey.OP_READ);// 将client注册
						// 创建buffer
						ByteBuffer buffer = ByteBuffer.allocate(100);
						// 将buffer对象附加到SelectionKey上 //所以在下面可以使用key.attachment
						// 来获得这个buffer对象
						clientKey.attach(buffer);
					}
					if (key.isReadable()) {// 只有当有输入的时候才会进入代码块
						SocketChannel client = (SocketChannel) key.channel();
						ByteBuffer output = (ByteBuffer) key.attachment();
						client.read(output);

						if (getString(output).equals("quit")) {
							key.cancel();// 要求取消该密钥的通道与其选择器的注册。
							key.channel().close();// 关闭通道
						}else if (getString(output).equals("pic")) {
							client.write(picBuffer);
							picBuffer.rewind();
							output.clear();		
						}else {
							System.out.println("接收数据：" + getString(output));
							client.write(getByteBuffer("接收数据"));
							output.flip();
							client.write(output);
							output.compact();
						}
					}
					// 这里就有一个问题 应该是在每次有读取的时候选择返回输出，就是说应该在上面的方法里写，而不是在下面那个
					// 每次循环都会进的方法里面写
					// if (key.isWritable()) {// 每次循环都会进isWritable
					// SocketChannel client = (SocketChannel) key.channel();
					// ByteBuffer output = (ByteBuffer) key.attachment();
					// output.flip();// 将buffer切换到写模式
					// client.write(output);
					// output.compact();// 保存之前未读的数据
					// }
				} catch (IOException e) {
					key.cancel();// 要求取消该密钥的通道与其选择器的注册。
					try {
						key.channel().close();// 关闭通道
					} catch (IOException e1) {
					}
					errorLogger.info("IOException" + e);
				} catch (CancelledKeyException e) {
					System.out.println("断开连接");
					errorLogger.info("CancelledKeyException" + e);
					continue;
				}
			}
		}

	}

	public static ByteBuffer getByteBuffer(String str) {

		return ByteBuffer.wrap(str.getBytes());

	}

	public static String getString(ByteBuffer buffer) {
		String string = "";
		try {
			for (int i = 0; i < buffer.position() - 2; i++) {// -2抛弃 \r\n的换行符
				string += (char) buffer.get(i);
			}
			return string;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
