package learnJavaNetProgam.ServerSocket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDaytimeServer {

	public final static int PORT = 1555;
	public final static Logger auditLogger = Logger.getLogger("requests");
	public final static Logger errorLogger = Logger.getLogger("errors");

	public static void main(String args[]) {
		ExecutorService pool = Executors.newFixedThreadPool(50);
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					Socket connection = server.accept();
					Callable<Void> task = new DaytimeTask(connection);
					pool.submit(task);
				} catch (IOException e) {
					errorLogger.log(Level.SEVERE, "accept error", e);
				} catch (RuntimeException e) {
					errorLogger.log(Level.SEVERE, "unexpected error " + e.getMessage(), e);
				}
			}
		} catch (IOException e) {
			errorLogger.log(Level.SEVERE, "Couldn't start server", e);
		} catch (RuntimeException ex) {
			errorLogger.log(Level.SEVERE, "Couldn't start server: " + ex.getMessage(), ex);
		}
	}

	private static class DaytimeTask implements Callable<Void> {

		private Socket connection;

		public DaytimeTask(Socket connection) {
			this.connection = connection;
		}

		@Override
		public Void call() throws Exception {

			try {
				Date now = new Date();
				auditLogger.info(now + " " + connection.getRemoteSocketAddress());
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				out.write(now.toString() + "\r\n");
				out.flush();
			} catch (IOException e) {
				// 客户端断开连接
			} finally {
				try {
					connection.close();
				} catch (IOException e) {// 忽略
				}
			}

			return null;
		}
	}

}
