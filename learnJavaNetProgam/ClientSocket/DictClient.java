package learnJavaNetProgam.ClientSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;

public class DictClient {
	public static final String SERVER = "dict.org";
	public static final int PORT = 2628;
	public static final int TIMEOUT = 15000;
	public static final String QUERY = "fd-eng-lat";

	public static void main(String args[]) {
		
		try (Socket socket = new Socket(SERVER,PORT)){
			
			socket.setSoTimeout(TIMEOUT);
			OutputStream out = socket.getOutputStream();
			Writer writer = new OutputStreamWriter(out, "UTF-8");
			writer = new BufferedWriter(writer);
			
			InputStream in = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			
			for(String word :args){
				define(word, writer, br);
			}
			
			writer.write("quit\r\n");
			writer.flush();
		} catch (Exception e) {
			System.err.println(e);
		}
	
	}
	
	static void define(String words, Writer writer,BufferedReader br)throws IOException,UnsupportedEncodingException{
		
		writer.write("DEFINE "+QUERY+" "+ words +"\r\n");
		writer.flush();
		
		for (String line = br.readLine(); line != null ; line = br.readLine() ) {
			
			if (line.startsWith("250 ")) {//ok
				return;
			}else if (line.startsWith("552 ")) {//无匹配
				System.out.println("找不到 "+ words);
				return;
			}else if(line.matches("\\d\\d\\d .*")){
				continue;
			}else if (line.trim().equals(".")) {
				continue;
			}else {
				System.out.println(line);
			}
			
			
		}
		
	}
	

}
