package learnJavaNetProgam.UDP;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class UDPTimeClient {

	public final static int port = 37;
	public final static String DEFAULT_HOST = "time.nist.gov";

	public static void main(String[] args) {
		InetAddress host;

		try {
			if (args.length > 0) {
				host = InetAddress.getByName(args[0]);
			} else {
				host = InetAddress.getByName(DEFAULT_HOST);
			}
		} catch (RuntimeException | UnknownHostException e) {
			System.out.println("usage: java UDPTimeClient [host]");
			return;
		}

		UDPPoke poker = new UDPPoke(host, port);
		byte[] response = poker.poke();

		if (response == null) {
			System.out.println("no response within allotted time");
			return;
		} else if (response.length != 4) {
			System.out.println("unrecongized responsed format");
			return;
		}

		long diff = 2208988800l;

		long secondsSince1900 = 0;
		for (int i = 0; i < 4; i++) {
			secondsSince1900 = (secondsSince1900 << 8) | (response[i] & 0x000000ff);
		}
		long secondsSince1970 = secondsSince1900 - diff;
		long msSince1970 = secondsSince1970 * 1000;
		Date time = new Date(msSince1970);
		System.out.println(time);
	}

}
