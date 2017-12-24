package learnInternet;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIP {

	public GetIP(String a[]) {
		for (String name : a) {
			showIP(name);
		}
	}

	public static void main(String args[]) {
		new GetIP(args);
	}

	public void showIP(String a) {
		try {
			InetAddress[] addresses = InetAddress.getAllByName(a);
			for(InetAddress address :addresses){
				System.out.println(address);
			}
			
		} catch (UnknownHostException e) {
			System.out.println("找不到" + a );
		}
	}

}
