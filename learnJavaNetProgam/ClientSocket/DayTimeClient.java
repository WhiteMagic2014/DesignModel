package learnJavaNetProgam.ClientSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DayTimeClient {
	public static void main(String args[]) throws IOException, ParseException {
		String hostname = args.length > 0 ? args[0] : "time.nist.gov";

		try (Socket socket = new Socket(hostname, 13)) {
			socket.setSoTimeout(15000);

			InputStream in = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			StringBuilder sb = new StringBuilder();

			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}
			
			System.out.println(parseDate(sb.toString()));

		}

	}

	static Date parseDate(String s) throws ParseException {
		String[] pieces = s.split(" ");
		String datetime = pieces[1] + " " + pieces[2] + " UTC";
		
		DateFormat df = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return df.parse(datetime);

	}

}
