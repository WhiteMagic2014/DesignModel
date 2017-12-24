package learnJavaNetProgam.ClientSocket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketInfo {
	public static void main(String args[]) {
		for (String host : args) {

			Socket socket;
			try {
				socket = new Socket(host, 80);
				System.out.println(socket.toString());
				System.out.println("Connected to " + socket.getInetAddress() + " on port " + socket.getPort()
						+ " from port " + socket.getLocalPort() + " of " + socket.getLocalAddress());
				System.out.println();
			} catch (UnknownHostException e) {
				System.err.println("can't find " +host);
			} catch (SocketException e) {
				System.err.println("could not connect to " + host);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
