package learnInternet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetLocalHost {

	public GetLocalHost() {
		showLocalHost();
	}

	public static void main(String args[]) {
		new GetLocalHost();
	}

	public void showLocalHost() {
		try {
			InetAddress me = InetAddress.getLocalHost();
			System.out.println(me);
			System.out.println(me.getHostAddress());
			System.out.println(me.hashCode());
			
		} catch (UnknownHostException e) {
			System.out.println("找不到本机LocalHost");
		}
	}

}
