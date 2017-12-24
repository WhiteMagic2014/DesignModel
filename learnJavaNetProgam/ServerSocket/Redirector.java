package learnJavaNetProgam.ServerSocket;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Redirector {
	private static final Logger logger = Logger.getLogger("Redirector");

	private final int port;
	private final String newSite;
	
	
	public static void main(String[] args){
		int theport;
		String thesite;
		
//		try {
//			thesite =args[0]; //要跳转的地址
//			if (thesite.endsWith("/")){
//				thesite = thesite.substring(0,thesite.length()-1);
//			}
//			
//		} catch (RuntimeException e) {
//			System.out.println("Usage: java Redirector http://www.newsite.com/ port");
//			return;
//		}
//		
//		try{
//			theport = Integer.parseInt(args[1]);//本地监听的端口
//		}catch(RuntimeException e){
//			theport = 80;
//		}
		
		theport = 1333;
		thesite = "http://www.bilibili.com";
		
		Redirector redirector = new Redirector(thesite, theport);
		redirector.start();
		
	}

	public Redirector(String newSite, int port) {
		this.port = port;
		this.newSite = newSite;
	}

	public void start() {
		try (ServerSocket server = new ServerSocket(port)) {
			
			logger.info("Redirecting connsctions on port "+ server.getLocalPort()+ " to "+ newSite);
			
			while(true){
				try {
					Socket s = server.accept();
					Thread t = new RedirectThread(s);
					t.start();
				} catch (IOException e) {
					logger.warning("exception accepting connection");
				} catch (RuntimeException e) {
					logger.log(Level.SEVERE, "unexpected error", e);
				}
			}
			

		} catch (BindException e) {
			logger.log(Level.SEVERE, "could not start server", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "error opening server socket", e);
		}

	}

	private class RedirectThread extends Thread {
		private final Socket connection;

		public RedirectThread(Socket s) {
			this.connection = s;
		}

		public void run() {
			try {
				Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "US-ASCII"));
				Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));

				StringBuilder request = new StringBuilder(80);
				while (true) {
					int c = in.read();
					if (c == '\r' || c == '\n' || c == -1) {
						break;
					}
					request.append((char) c);
				}

				String get = request.toString();
				
				String[] splits = get.split("\\s+"); //应该是用空格分割 而不是书上写的那个 //w*
				String theFile = splits[1];
				
//				for(String a:splits){
//					System.out.print(a+" ");
//				}
//				System.out.println();


				// 如果是http/1.0版本或以前的版本，则发一个MIME首部
				if (get.indexOf("HTTP") != -1) {
					out.write("HTTP/1.0 302 FOUND\r\n");
					Date now = new Date();
					out.write("Date: " + now + "\r\n");
					out.write("Server:Redirector 1.1\r\n");
					out.write("Location:" + newSite + theFile + "\r\n");
					out.write("Content-type: text/html\r\n\r\n");
					out.flush();
				}
				
				//并不是所有浏览器都支持重定向，需要生成html指出文档生成到哪
				out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
				out.write("<BODY><H1>Document moved</H1>\r\n");
				out.write("The document "+theFile+" has moved to\r\n<A HREF =\"" + newSite + theFile +"\">"+newSite
						+ theFile+ "</A>.\r\n Please update your bookmarks<P>");
				out.write("</BODY></HTML>\r\n");
				out.flush();
				
				logger.log(Level.INFO,"Redirected "+connection.getRemoteSocketAddress());
			} catch (IOException e) {
				logger.log(Level.WARNING, "error talk to " + connection.getRemoteSocketAddress(), e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
				}
			}
		}

	}

}
