package learnJavaNetProgam;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 练习 读取服务器的数据
 * 
 * @author chenhaoyu
 */
public class MagicURLConnection {

	public static void main(String args[]) {

		MagicURLConnection m = new MagicURLConnection();

		String aString = "http://img.ngacn.cc/attachments/mon_201705/21/f0Qhls-59bzXkZ5iT1kS8g-4r.gif";

		String bString = "http://www.lolcats.com";

		String cString = "https://www.bilibili.com";

		// m.downloadBinaryData(aString);
		// m.readAllHeads(aString);
		m.readEncodeServerDate(cString);

	}

	/**
	 * 显示整个http首部
	 * 
	 * getHeaderField返回的值都是string类型
	 * 
	 * @param urlString
	 */
	public void readAllHeads(String urlString) {

		try {
			URL u = new URL(urlString);
			URLConnection uc = u.openConnection();
			// 查询是否在过去24小时内更新过，如果没更新就不下载东西
			// uc.setIfModifiedSince(new Date(new Date().getTime() -
			// 24*60*60*1000).getTime());

			// 查看本地发送给服务器的请求
			// Map<String, List<String>> map = uc.getRequestProperties();
			// if (map == null) {
			// Iterator iterator = map.keySet().iterator();
			// while (iterator.hasNext()) {
			// String key = iterator.next().toString();
			// System.out.println(key + ": ");
			// List<String> list = map.get(key);
			// for (String a : list) {
			// System.out.println(a);
			// }
			// }
			// } else {
			// System.out.println("map is null");
			// }

			for (int i = 1;; i++) {
				String header = uc.getHeaderField(i);
				if (header == null)
					break;
				System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
			}

		} catch (MalformedURLException e) {
			System.err.println(urlString + " is not a parseable URL");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取指定的首部字段
	 * 
	 * @param urlString
	 */
	public void readHead(String urlString) {

		try {
			URL u = new URL(urlString);
			URLConnection uc = u.openConnection();
			System.out.println("Content-type: " + uc.getContentType());

			if (uc.getContentEncoding() != null) {
				System.out.println("Content-encoding: " + uc.getContentEncoding());
			}

			if (uc.getDate() != 0) {
				System.out.println("Date: " + new Date(uc.getDate()));
			}

			if (uc.getLastModified() != 0) {
				System.out.println("Last modified: " + new Date(uc.getLastModified()));
			}

			if (uc.getExpiration() != 0) {
				System.out.println("Expiration date: " + new Date(uc.getExpiration()));
			}

			if (uc.getContentLength() != -1) {
				System.out.println("Content-length: " + uc.getContentLength());
			}

		} catch (MalformedURLException e) {
			System.err.println(urlString + " is not a parseable URL");
		} catch (IOException ioe) {
			System.err.println(ioe);
		}

	}

	/**
	 * 用URLConnection对象从一个URL获取数据基本步骤 1.创建URL对象
	 * 2.调用URL对象的openConnection方法，以此来获取这个URL的URLConnection对象
	 * 3.调用URLConnection的getInputStream方法 4.对获取到的inputstream流进行处理
	 * 
	 * @param urlString
	 */
	public void readServerDate(String urlString) {

		try {
			URL u = new URL(urlString); // 1
			URLConnection uc = u.openConnection();// 2
			try (InputStream in = uc.getInputStream()) { // 3
				InputStream buffer = new BufferedInputStream(in); // 4
				Reader r = new InputStreamReader(buffer);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char) c);
				}
			}
		} catch (MalformedURLException e) {
			System.err.println(urlString + " is not a parseable URL");
		} catch (IOException ioe) {
			System.err.println(ioe);
		}

	}

	/**
	 * readServerDate的强化版 首先读取首部字段 根据返回的编码方式来解码
	 * 如果没有指定编码方式，就用http默认的编码方式（ISO-8859-1） 如果遇到非文本类型则抛出异常
	 * 
	 * @param urlString
	 */
	public void readEncodeServerDate(String urlString) {

		try {
			String encoding = "ISO-8859-1";
			URL u = new URL(urlString);
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			int encodingStart = contentType.indexOf("charset=");
			if (encodingStart != -1) {
				encoding = contentType.substring(encodingStart + 8);
			}

			try (InputStream in = uc.getInputStream()) {
				InputStream buffer = new BufferedInputStream(in);
				Reader r = new InputStreamReader(buffer, encoding);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char) c);
				}
			}
		} catch (MalformedURLException e) {
			System.err.println(urlString + " is not a parseable URL");
		} catch (UnsupportedEncodingException ex) {
			System.err.println("Sever send an encoding Java does not support: " + ex.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe);
		}

	}

	/**
	 * 下载二进制文件
	 * 
	 * @param urlString
	 */
	public void downloadBinaryData(String urlString) {

		try {
			URL u = new URL(urlString);
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			int contentLength = uc.getContentLength();
			if (contentType.startsWith("text/") || contentLength == -1) {
				throw new IOException("this is not a binary fine");
			}
			// System.out.println(contentType + " " + contentLength);
			try (InputStream in = uc.getInputStream()) {
				InputStream buffer = new BufferedInputStream(in);
				byte[] data = new byte[contentLength];
				int offset = 0;
				while (offset < contentLength) {
					int bytesRead = buffer.read(data, offset, data.length - offset);
					if (bytesRead == -1)
						break;
					offset += bytesRead;
				}
				if (offset != contentLength) {
					throw new IOException("Only read " + offset + " bytes;Expected " + contentLength + " bytes");
				}
				String filename = u.getFile();
				filename = filename.substring(filename.lastIndexOf('/') + 1);
				String path = "/Users/chenhaoyu/Desktop/testdownload/";
				// System.out.println(path+filename);
				try (FileOutputStream fout = new FileOutputStream(path + filename)) {
					fout.write(data);
					fout.flush();
				}
			}
		} catch (MalformedURLException e) {
			System.err.println(urlString + " is not a parseable URL");
		} catch (IOException ex) {
			System.err.println(ex);
		}

	}

}
