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

public class PooledDayTimeServer {

	public static final int port = 1333;

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(50);

		try (ServerSocket server = new ServerSocket(port)) {

			while (true) {
				try {
					Socket connection = server.accept();
					Callable<Void> task = new DaytimeTask(connection);
					pool.submit(task);
				} catch (IOException ex) {
				}

			}
		} catch (IOException e) {
			System.err.println("can't start server");
		}

	}

	private static class DaytimeTask implements Callable<Void> {
		private Socket connection;

		public DaytimeTask(Socket connection) {
			this.connection = connection;
		}

		@Override
		public Void call() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString() + "\r\n");
				out.flush();

			} catch (IOException e) {
				System.err.println(e);
			} finally {
				try {
					connection.close();
				} catch (IOException e2) {
				}
			}
			return null;
		}
	}

}
