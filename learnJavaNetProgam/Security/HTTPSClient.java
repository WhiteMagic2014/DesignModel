package learnJavaNetProgam.Security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSClient {

	public static void main(String[] args) {
//
//		if (args.length == 0) {
//			System.out.println("请输入一个host");
//			return;
//		}
//
//		String host = args[0];
		String host = "www.baidu.com";
		int port = 443;//默认https端口
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = null;

		try {
			socket = (SSLSocket) factory.createSocket(host, port);
			String[] supported = socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supported);
			
			Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			// https在get中需要完全的url
			out.write("GET http://" + host + "/ HTTP/1.1\r\n");
			out.write("HOST: " + host + "\r\n");
			out.write("\r\n");
			out.flush();

			// 读取首部
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s;
			while (!(s = in.readLine()).equals("")) { // 不为空行时读取 和 非null还是有区别的，空行 不是 null ，所以会一直读完
				System.out.println(s);
			}
			System.out.println();

			// 读取长度
			String contentlength = in.readLine();
			int length = Integer.MAX_VALUE;
			try {
				length = Integer.parseInt(contentlength.trim(), 16);
			} catch (NumberFormatException e) {
				// 对方服务器在响应的第一行没有发送 content-length
			}
			System.out.println(contentlength);

			int c;
			int i = 0;
			while ((c = in.read()) != -1 && i++ < length) {
				System.out.print((char)c);
			}

		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (socket != null)
					socket.close();

			} catch (Exception e2) {
			}

		}
	}

}
