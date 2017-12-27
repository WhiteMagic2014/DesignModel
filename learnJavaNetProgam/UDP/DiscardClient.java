package learnJavaNetProgam.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiscardClient {

	public static final int port = 1333;

	public static void main(String[] args) {

		String hostname = args.length > 0 ? args[0] : "localhost";

		try (DatagramSocket socket = new DatagramSocket()) { // 不需要关心绑定到哪一个端口
			InetAddress server = InetAddress.getByName(hostname);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String theLine = userInput.readLine();
				if (theLine.equals(".")) {
					break;
				}
				byte[] data = theLine.getBytes();
				DatagramPacket theOutput = new DatagramPacket(data, data.length, server, port);
				socket.send(theOutput);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
