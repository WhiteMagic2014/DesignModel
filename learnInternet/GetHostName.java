package learnInternet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostName {
	public GetHostName(String ips[]) {
		for (String ip : ips) {
			showIP(ip);	
		}
	}

	public static void main(String args[]) {
		new GetHostName(args);
	}

	public void showIP(String ip) {
		try {
			InetAddress ia = InetAddress.getByName(ip);
			System.out.println(ia.getCanonicalHostName());
		} catch (UnknownHostException e) {
			System.out.println("找不到");
		}
	}
}
