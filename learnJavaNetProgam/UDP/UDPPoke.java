package learnJavaNetProgam.UDP;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPPoke {

	private int bufferSize;
	private int timeout;
	private InetAddress host;
	private int port;

	public UDPPoke(InetAddress host, int port, int bufferSize, int timeout) {
		this.bufferSize = bufferSize;
		this.timeout = timeout;
		this.host = host;
		if (port < 1 || port > 65535) {
			throw new IllegalArgumentException("port out of range");
		}
		this.port = port;
	}

	public UDPPoke(InetAddress host, int port, int bufferSize) {
		this(host, port, bufferSize, 30000);
	}

	public UDPPoke(InetAddress host, int port) {
		this(host, port, 8192, 30000);
	}

	public byte[] poke() {

		try (DatagramSocket socket = new DatagramSocket(0)) {
			DatagramPacket outgoing = new DatagramPacket(new byte[1], 1, host, port);
			socket.connect(host, port);
			socket.setSoTimeout(timeout);
			socket.send(outgoing);

			DatagramPacket incoming = new DatagramPacket(new byte[bufferSize], bufferSize);
			socket.receive(incoming);// 阻塞到接受响应为止

			int numBytes = incoming.getLength();
			byte[] response = new byte[numBytes];
			System.arraycopy(incoming.getData(), 0, response, 0, numBytes);

			return response;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		InetAddress host;
		int port = 0;

		try {
			host = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
		} catch (RuntimeException | UnknownHostException e) {
			System.out.println("Usage:java UDPPoke host port");
			return;
		}

		try {
			UDPPoke poker = new UDPPoke(host, port);
			byte[] response = poker.poke();

			if (response == null) {
				System.out.println("no response within allotted time");
				return;
			}
			String result = new String(response, "US-ASCII");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
