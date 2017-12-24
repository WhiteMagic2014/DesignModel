package learnJavaNetProgam.ServerSocket;

import java.io.File;
import java.io.IOException;

public class filetest {

	public static void main(String[] args) {
		File f = new File("/Users/chenhaoyu/Downloads/magictest");

		System.out.println(f.getPath());
		try {
			System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(f.getAbsolutePath());

		System.out.println();
		
		File g = new File("../filetest.java");
		
		System.out.println(g.getPath());
		try {
			System.out.println(g.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(g.getAbsolutePath());
		
		
	}

}
