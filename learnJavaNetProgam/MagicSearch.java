package learnJavaNetProgam;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MagicSearch {

	public static void main(String args[]) {
		String target ="";
//		for (int i = 0; i < args.length; i++) {
//			target += args[i] + " ";
//		}
//		target = target.trim();
		
		target = "搜索的内容";
		
		MagicURL urlString = new MagicURL("http://www.baidu.com/s?");
		urlString.add("w", target);
		
		try {
			URL u = new URL(urlString.getQuery());
			
			
			try(InputStream in = new BufferedInputStream(u.openStream())){
				
				InputStreamReader inr = new InputStreamReader(in);
				int c;
				while( (c = inr.read())!= -1 ){
					System.out.print((char)c);
				}
				
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		

	}

	
	
}
