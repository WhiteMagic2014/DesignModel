package learnJavaNetProgam.ServerSocket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultithreadDaytimeServer {

	public static final int port = 1333;

	public static void main(String args[]) {

		try (ServerSocket server = new ServerSocket(port)) {

			while (true) {
				try {
					Socket connection = server.accept();
					Thread task = new DaytimeThread(connection);
					task.start();
				} catch (IOException e) {
				}
			}
		} catch (IOException e) {
			System.err.println("can't start server");
		}
	}

	private static class DaytimeThread extends Thread {

		private Socket connection;

		public DaytimeThread(Socket connection) {
			this.connection = connection;
		}

		@Override
		public void run() {

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
				} catch (IOException e) {
				}
			}

		}

	}

}
