package learnJavaNetProgam.ServerSocket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class SingleFileHTTPServer {

	private static final Logger logger = Logger.getLogger("SingleFileHTTPServer");

	private final byte[] content;
	private final byte[] header;
	private final int port;
	private final String encoding;

	public static void main(String[] args) {

		// 设置端口
		int port;
		try {
			port = Integer.parseInt(args[1]);
			if (port < 1 || port > 65535) {
				port = 80;//80可能没权限
			}
		} catch (RuntimeException e) {
			port = 80;
		}

		String encoding = "UTF-8";
		if (args.length > 2) {
			encoding = args[2];
		}

		try {
			Path path = Paths.get(args[0]);
			byte[] data = Files.readAllBytes(path);
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
			SingleFileHTTPServer server = new SingleFileHTTPServer(data, encoding, contentType, port);
			server.start();

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java SingleFileHTTPServer filename port encoding");
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}

	}

	public SingleFileHTTPServer(byte[] data, String encoding, String mimeType, int port) {
		this.content = data;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n" + "Server: OneFile 2.0\r\n" + "Content-length: " + this.content.length
				+ "\r\n" + "Content-type: " + mimeType;
		this.header = header.getBytes(Charset.forName("US-ASCII"));
	}

	public SingleFileHTTPServer(String data, String encodString, String mimeType, int port) {
		this(data.getBytes(), encodString, mimeType, port);
	}

	public void start() {
		ExecutorService pool = Executors.newFixedThreadPool(100);
		try (ServerSocket server = new ServerSocket(this.port)) {
			logger.info("Accepting connections on port " + server.getLocalPort());
			logger.info("Date to be sent:");
			logger.info(new String(this.content, encoding));

			while (true) {
				try {
					Socket connection = server.accept();
					pool.submit(new HTTPHander(connection));
				} catch (IOException e) {
					logger.log(Level.WARNING, "Exception accepting connection", e);
				} catch (RuntimeException e) {
					logger.log(Level.SEVERE, "Unexpected orror", e);
				}
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not start server", e);
		}
	}

	private class HTTPHander implements Callable<Void> {
		private final Socket connection;

		public HTTPHander(Socket connection) {
			this.connection = connection;
		}

		@Override
		public Void call() throws Exception {
			try {
				OutputStream out = new BufferedOutputStream(connection.getOutputStream());
				InputStream in = new BufferedInputStream(connection.getInputStream());
				// 只读第一行,
				StringBuilder request = new StringBuilder(80);
				while (true) {
					int c = in.read();
					if (c == '\r' || c == '\n' || c == -1) {
						break;
					}
					request.append((char) c);
				}
				// 如果是http/1.0版本或以后版本，则发一个MIME首部
				if (request.toString().indexOf("HTTP") != -1) {
					out.write(header);
				}
				out.write(content);
				out.write("\r\n".getBytes());
				out.flush();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error writing to client", e);
			} finally {
				connection.close();
			}
			return null;
		}
	}

}
