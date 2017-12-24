package learnJavaNetProgam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 此类为get 和 post 方法创造urlstring 以及 请求的键值对 
 * get 方法使用时，构建方法中传入初始url，使用getUrl获得完整的编码后url 
 * post 方法使用时，构造方法无需传入url，使用getQuery 仅获得编码后的键值对
 * 此外还有第三种构造方法可指定编码 解码 方式 默认为utf-8
 * @author chenhaoyu
 *
 */
public class MagicURL {

	private String CODE_WAY;

	public static void main(String args[]) {
		MagicURL m = new MagicURL("magic2014.com/search?");
		m.add("键", "值");

		System.out.println(m.getQuery());
		System.out.println(m.getUrl());

		m.decode(m.getQuery());
		m.decode(m.getUrl());

	}

	String url;
	private StringBuilder sb = new StringBuilder();

	public MagicURL() {
		this.url = "";
		this.CODE_WAY = "UTF-8";
	}

	public MagicURL(String url) {
		this.url = url;
		this.CODE_WAY = "UTF-8";
	}

	public MagicURL(String url, String codeway) {
		this.url = url;
		this.CODE_WAY = codeway;
	}

	public synchronized void add(String name, String value) {
		sb.append('&');
		encode(name, value);
	}

	/**
	 * 输入键值 构造编码后的键值对
	 * 
	 * @param name
	 * @param value
	 */
	private synchronized void encode(String name, String value) {
		try {
			sb.append(URLEncoder.encode(name, CODE_WAY));
			sb.append('=');
			sb.append(URLEncoder.encode(value, CODE_WAY));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("VM does not support " + CODE_WAY);
		}

	}

	public synchronized String getQuery() {
		return sb.toString();
	}

	public synchronized String getUrl() {
		return url + sb.toString();

	}

	@Override
	public String toString() {
		return getQuery();
	}

	/**
	 * URLDecoder 解码
	 * 
	 * @param input
	 */
	public synchronized void decode(String input) {
		try {
			String output = URLDecoder.decode(input, CODE_WAY);
			System.out.println(output);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("VM does not support " + CODE_WAY);
		}

	}

}
