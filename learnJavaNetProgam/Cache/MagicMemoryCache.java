package learnJavaNetProgam.Cache;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认情况下java不完成缓存，要安装URL类使用的系统级缓存 需要有一下3个类
 * ResponseCache，CacheResponse，CacheRequest 的3个具体子类
 * 安装了缓存后，只要系统尝试加载一个新的URL，他就会先去缓存中查找。
 * 
 * 使用以下完成安装：
 * ResponseCache.setDefault(ResponseCache responseCache);
 * 
 * 在ResponseCache中提供了2个抽象方法，可以存储和获取系统缓存中的数据：
 * put（）方法返回一个CacheRequest对象，他包装了一个OutputStream，URL将把读取的可缓存数据写入这个输出流。
 * get（）方法从缓存中获取数据和首部，包装在CacheResponse对象中返回。如果所需的URI不在缓存中，则返回null，并重新加载这个URI
 * 
 * 
 * 
 * @author chenhaoyu
 *
 */
public class MagicMemoryCache extends ResponseCache {

	private final Map<URI, MagicCacheResponse> responses = new ConcurrentHashMap<URI, MagicCacheResponse>();
	private final int maxEntries;

	public MagicMemoryCache(int maxEntries) {
		this.maxEntries = maxEntries;
	}

	public MagicMemoryCache() {
		this(100);
	}

	@Override
	public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException {

		if ("GET".equals(rqstMethod)) {
			MagicCacheResponse response = responses.get(uri);
			// 检查过期
			if (response != null && response.isExpired()) {
				responses.remove(response);
				response = null;
			}
			return response;
		} else {
			return null;
		}
	}

	@Override
	public CacheRequest put(URI uri, URLConnection conn) throws IOException {
		if (responses.size() >= maxEntries) {
			return null;
		}

		CacheControl control = new CacheControl(conn.getHeaderField("Cache-Control"));

		if (control.noStore()) {
			return null;
		} else if (!conn.getHeaderField(0).startsWith("GET")) {
			// 只缓存get
			return null;
		}

		MagicCacheRequest request = new MagicCacheRequest();
		MagicCacheResponse response = new MagicCacheResponse(request, conn, control);

		responses.put(uri, response);

		return request;
	}

}
