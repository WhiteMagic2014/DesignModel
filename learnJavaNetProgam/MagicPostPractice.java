package learnJavaNetProgam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MagicPostPractice {

	private MagicURL query = new MagicURL();
	private URL url;

	public MagicPostPractice(URL url) {
		if (!url.getProtocol().toLowerCase().startsWith("http")) {
			throw new IllegalArgumentException("posting only works for http");
		}
		this.url = url;
	}

	public void add(String name, String value) {
		query.add(name, value);
	}

	public URL getUrl() {
		return this.url;
	}

	public InputStream post() throws IOException {
		URLConnection uc = url.openConnection();
		uc.setDoOutput(true);
		try (OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
			out.write(query.getQuery());
			out.write("\r\n");
			out.flush();
		}
		return uc.getInputStream();
	}

	public static void main(String args[]) {
		URL url;
		// 如果没有输入的url 则使用默认的测试url
		if (args.length > 0) {
			try {
				url = new URL(args[0]);
			} catch (MalformedURLException e) {
				System.err.println("java : MagicPostPractice url");
				return;
			}
		} else {
			try {
				url = new URL("http://192.168.1.3:8888");
			} catch (MalformedURLException e) {
				System.err.println(e);
				return;
			}
		}

		MagicPostPractice poster = new MagicPostPractice(url);
		poster.add("name", "magic");
		poster.add("email", "magic@white.com");

		try (InputStream in = poster.post()) {
			Reader r = new InputStreamReader(in);
			int c;
			while ((c = r.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e);
		}	

	}

}
