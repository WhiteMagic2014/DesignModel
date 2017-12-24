package learnJavaNetProgam;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * 关于HttpURLConnection的相关练习 HttpURLConnection 是 URLConnection的抽象子类
 * 有7个请求方法：GET,POST,HEAD,PUT,DELETE,OPTIONS,TRACE
 * 
 * @author chenhaoyu
 *
 */
public class MagicHttpURLConnection {

	public static void main(String args[]) {
		String urlString;
		MagicHttpURLConnection m = new MagicHttpURLConnection();

		if (args.length > 0) {
			urlString = args[0];
		} else {
			urlString = "https://www.bilibili.com/";
		}

		// m.LastModified(urlString);
		m.SourceViewer(urlString);

	}

	/**
	 * HEAD 的使用和GET十分相似，他告诉服务器只返回HTTP首部，不用实际发送文件 这个例子是显示服务器上一个文件最后一次修改的时间
	 */
	public void LastModified(String urlString) {

		try {
			URL u = new URL(urlString);
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("HEAD");
			System.out.println(u + "\nwas last modified at " + new Date(http.getLastModified()));
		} catch (MalformedURLException e) {
			System.err.println(urlString + "is not a URL I understand,maybe lost head -- http://");
		} catch (IOException e) {
			System.err.println(e);
		}
		System.out.println();
	}

	/**
	 * 包含响应码和消息的SourceViewer
	 */
	public void SourceViewer(String urlString) {

		try {
			URL u = new URL(urlString);
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
			int code = http.getResponseCode();
			String response = http.getResponseMessage();

			// 返回首部
			System.out.println(http.getHeaderField(0)); // 返回第一行
			for (int i = 1;; i++) {
				String header = http.getHeaderField(i);
				String key = http.getHeaderFieldKey(i);
				if (header == null || key == null) {
					break;
				}
				System.out.println(key + ": " + header);
			}
			System.out.println();

			try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
				String message = "";
				String line = null;
				while ((line = br.readLine()) != null) {
					message += "\n" + line;
				}
				System.out.println(message);
			} catch (IOException e) {
				printFromStream(http.getErrorStream());
			}

//			try (InputStream in = http.getInputStream()) {
//				printFromStream(in);
//			} catch (IOException e) {
//				printFromStream(http.getErrorStream());
//			}

		} catch (MalformedURLException e) {
			System.err.println(urlString + "is not a URL I understand,maybe lost head -- http://");
		} catch (IOException e) {
			System.err.println(e);

		}
	}

	private void printFromStream(InputStream raw) throws IOException {
		try (InputStream in = new BufferedInputStream(raw)) {
			Reader r = new InputStreamReader(in);
			int c;
			while ((c = r.read()) != -1) {
				System.out.print((char) c);
			}
		}
	}

}
