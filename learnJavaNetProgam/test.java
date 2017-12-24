package learnJavaNetProgam;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class test {

	public static void main(String[] args) {
		String urls[] = { "http://www.lolcats.com" };
		String a[] = { "https://i2.hdslb.com/bfs/face/da52672efa45be13192553d14c45e70f094fa02c.jpg@72w_72h.webp" };

		// downloadobj(urls);
		

	}
	
	private static void download(String args[]) {
		if (args.length > 0) {
			InputStream in = null;

			try {
				URL u = new URL(args[0]);
				in = u.openStream();

				in = new BufferedInputStream(in);

				Reader r = new InputStreamReader(in);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char) c);
				}

			} catch (MalformedURLException e) {
				System.err.println(args[0] + "is not a parseable URL");
			} catch (IOException ex) {
				System.err.println(ex);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}

	}

	private static void download2(String args[]) {

		if (args.length > 0) {

			try {
				URL u = new URL(args[0]);
				try (InputStream in = u.openStream()) {
					int c;
					while ((c = in.read()) != -1) {
						System.out.write(c);
					}
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	private static void downloadobj(String args[]) {
		if (args.length > 0) {

			try {
				URL u = new URL(args[0]);

				Class<?>[] types = new Class[3];
				types[0] = String.class;
				types[1] = Reader.class;
				types[2] = InputStream.class;
				Object o = u.getContent(types);

				if (o instanceof String) {

					System.out.println("string");
					System.out.println("got " + o.getClass().getName());

				} else if (o instanceof Reader) {
					System.out.println("Reader");

					int c;
					Reader r = (Reader) o;
					while ((c = r.read()) != -1) {
						System.out.print((char) c);
					}
					r.close();

				} else if (o instanceof InputStream) {
					System.out.println("InputStream");

					int c;
					InputStream in = (InputStream) o;
					while ((c = in.read()) != -1) {
						System.out.write(c);
					}
					in.close();

				} else {
					System.out.println("error unexpected type " + o.getClass());
				}

			} catch (MalformedURLException ex) {
				System.err.println(ex);
			} catch (IOException e) {
				System.err.println(e);
			}

		}
	}

}
