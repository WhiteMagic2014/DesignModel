package learnJavaNetProgam.Cache;

import java.util.Date;

/**
 * 解析和查询Head中的Cache-Control部分
 * @author chenhaoyu
 */
public class CacheControl {

	private Date maxAge = null;
	private Date sMaxAge = null;
	private boolean mustRevalidate = false;
	private boolean noCache = false;
	private boolean noStore = false;
	private boolean proxyRevalidate = false;
	private boolean publicCache = false;
	private boolean privateCache = false;

	public CacheControl(String s) {
		if (s == null || !s.contains(":")) {
			return;// 默认策略
		}

		String value = s.split(":")[1].trim();
		String[] components = value.split(",");

		Date now = new Date();
		for (String component : components) {

			try {
				component = component.trim().toLowerCase();
				if (component.startsWith("max-age=")) {
					int secondsInTheFuture = Integer.parseInt(component.substring(8));
					maxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
				} else if (component.startsWith("s-maxage=")) {
					int secondsInTheFuture = Integer.parseInt(component.substring(8));
					sMaxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
				} else if (component.startsWith("must-revalidate")) {
					mustRevalidate = true;
				} else if (component.startsWith("proxy-revalidate")) {
					proxyRevalidate = true;
				} else if (component.startsWith("no-cache")) {
					noCache = true;
				} else if (component.startsWith("public")) {
					publicCache = true;
				} else if (component.startsWith("private")) {
					privateCache = true;
				}
			} catch (RuntimeException ex) {
				continue;
			}

		}

	}

	public Date getMaxAge() {
		return maxAge;
	}

	public Date getSharedMaxAge() {
		return sMaxAge;
	}

	public boolean mustRevalidate() {
		return mustRevalidate;
	}

	public boolean proxyRevalidate() {
		return proxyRevalidate;
	}

	public boolean noCache() {
		return noCache;
	}

	public boolean noStore() {
		return noStore;
	}

	public boolean publicCache() {
		return publicCache;
	}

	public boolean privateCache() {
		return privateCache;
	}

}
