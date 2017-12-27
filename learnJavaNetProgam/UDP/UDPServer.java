package learnJavaNetProgam.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 一个泛用的udp父类
 * @author chenhaoyu
 */
public abstract class UDPServer implements Runnable {

	private final int port;
	private final int bufferSize;
	private final Logger logger = Logger.getLogger(UDPServer.class.getCanonicalName());
	private volatile boolean isShutdown = false;

	public UDPServer(int port, int bufferSize) {
		this.port = port;
		this.bufferSize = bufferSize;
	}

	public UDPServer(int port) {
		this.port = port;
		this.bufferSize = 8192;
	}

	@Override
	public void run() {
		byte[] buffer = new byte[bufferSize];

		try (DatagramSocket socket = new DatagramSocket(port)) {
			socket.setSoTimeout(10000);
			while (true) {
				if (isShutdown) {
					return;
				}

				DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

				try {
					socket.receive(incoming);
					this.respond(socket, incoming);

				} catch (SocketTimeoutException e) {
					if (isShutdown) {
						return;
					}
				} catch (IOException e) {
					logger.log(Level.WARNING, e.getMessage(), e);
				}

			} // end while
		} catch (SocketException e) {
			logger.log(Level.SEVERE, "could not bind to port: " + port, e);
		}

	}

	public abstract void respond(DatagramSocket socket, DatagramPacket request) throws IOException;

	public void shutDown() {
		this.isShutdown = false;
	}

}
