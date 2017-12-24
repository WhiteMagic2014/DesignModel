package learnJavaNetProgam.ClientSocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeCilent {
	private static final String HOSTNAME = "time.nist.gov";

	public static void main(String args[]) throws IOException, ParseException {
		Date d = TimeCilent.getDateFromNetwork();
		System.out.println("It is " + d);

	}

	public static Date getDateFromNetwork() throws IOException, ParseException {

		long differenceBetweenEpochs = 2208988800l;

		/*
		 * TimeZone gmt = TimeZone.getTimeZone("GMT"); Calendar epoch1900 =
		 * Calendar.getInstance(gmt); epoch1900.set(1900, 01, 01, 00, 00, 00);
		 * long epoch1900ms = epoch1900.getTime().getTime();
		 * 
		 * Calendar epoch1970 = Calendar.getInstance(gmt); epoch1970.set(1970,
		 * 01, 01, 00, 00, 00); long epoch1970ms =
		 * epoch1970.getTime().getTime();
		 * 
		 * long differenceInMS = epoch1970ms -epoch1900ms; long
		 * differenceBetweenEpochs = differenceInMS/1000;
		 * 
		 * System.out.println(differenceBetweenEpochs);
		 */

		try (Socket socket = new Socket(HOSTNAME, 37)) {

			socket.setSoTimeout(15000);
			InputStream in = socket.getInputStream();

			long secondsSince1900 = 0;
			for (int i = 0; i < 4; i++) {
				secondsSince1900 = (secondsSince1900 << 8) | in.read();
			}
			long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
			long msSince1970 = secondsSince1970 * 1000;

			Date time = new Date(msSince1970);
			return time;
		}
	}
}
