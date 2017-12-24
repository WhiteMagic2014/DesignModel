package learnJavaNetProgam.ServerSocket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import learnJavaNetProgam.AsciiPic;

public class DayTimeServer {

	public final static int port = 1333;

	public static void main(String args[]) {

		
		try (ServerSocket server = new ServerSocket(port)) {
			while (true) {
				try(Socket connection = server.accept()){
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString() + "\r\n");
					out.flush();
					connection.close();
				}catch (IOException e) {

				}
			}
			
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
