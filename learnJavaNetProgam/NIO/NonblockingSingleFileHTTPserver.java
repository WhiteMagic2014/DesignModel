package learnJavaNetProgam.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;


public class NonblockingSingleFileHTTPserver {

	private ByteBuffer contentBuffer;
	private int port = 1333;
	
	
	public static void main(String[] args){
		
		String filepath = "/Users/chenhaoyu/Downloads/learnIO/magic.txt";
		
		try {
			
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(filepath);
			Path file = FileSystems.getDefault().getPath(filepath);
			byte[] data = Files.readAllBytes(file);
			ByteBuffer input = ByteBuffer.wrap(data);
			
			String encoding ="UTF-8";
			
			NonblockingSingleFileHTTPserver server = new NonblockingSingleFileHTTPserver(input, encoding, contentType, 1333);
					
			server.run();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public NonblockingSingleFileHTTPserver(ByteBuffer data,String encoding,String MIMEType,int port) {
		this.port = port;
		String header = "HTTP/1.0 200 OK \r\n"
				+ "Server: NonblockingSingleFileHTTPserver\r\n"
				+ "Content-length: "+data.limit()+"\r\n"
				+ "Content-type: "+MIMEType+"\r\n\r\n";
		byte[] headerdata = header.getBytes(Charset.forName("US-ASCII"));
		
		ByteBuffer buffer = ByteBuffer.allocate(data.limit()+headerdata.length);
		
		buffer.put(headerdata);
		buffer.put(data);
		buffer.flip();
		this.contentBuffer =buffer;
	}
	
	public void run()throws IOException{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket ss = serverChannel.socket();
		Selector selector = Selector.open();
		InetSocketAddress address = new InetSocketAddress(port);
		ss.bind(address);
		
		System.out.println("listening for connections on port " + port);
		
		serverChannel.configureBlocking(false);
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		
		while(true){
			
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			
			Set<SelectionKey> readykeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readykeys.iterator();
			
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				
				
				try {
					
					if (key.isAcceptable()) {
						ServerSocketChannel server =(ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();
						System.out.println("accepted connection from " + channel);
						channel.configureBlocking(false);
						channel.register(selector, SelectionKey.OP_READ);
						
					}else if (key.isReadable()) {
						SocketChannel channel =(SocketChannel) key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(4096);
						channel.read(buffer);
						key.interestOps(SelectionKey.OP_WRITE);
						key.attach(contentBuffer.duplicate());
					}else if(key.isWritable()) {
						SocketChannel channel =(SocketChannel) key.channel();
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						if (buffer.hasRemaining()) {
							channel.write(buffer);
						}else {
							channel.close();
						}
					}
					
				} catch (IOException e) {
					key.cancel();
					try {
						key.channel().close();
					} catch (IOException e2) {}
					
				}
				
				
			}
			
			
			
		}
		
		
	}
	
	
}
