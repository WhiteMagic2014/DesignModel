package learnJavaNetProgam.Cache;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 一个简单的CacheResponse子类，他绑定到一个MagicCacheRequest 和一个 CacheControl 在这里 共享引用将数据从
 * 请求类（Request）传递到 响应类（Response）如果在文件中存储响应，那就要共享文件名
 * 
 * @author chenhaoyu
 *
 */
public class MagicCacheResponse extends CacheResponse {

	private final Map<String, List<String>> headers;
	private final MagicCacheRequest request;
	private final Date expires;
	private final CacheControl control;

	public MagicCacheResponse(MagicCacheRequest request, URLConnection uc, CacheControl control) throws IOException {
		this.request = request;
		this.control = control;
		this.expires = new Date(uc.getExpiration());
		this.headers = Collections.unmodifiableMap(uc.getHeaderFields());
	}

	@Override
	public InputStream getBody() throws IOException {
		return new ByteArrayInputStream(request.getData());
	}

	@Override
	public Map<String, List<String>> getHeaders() throws IOException {
		return headers;
	}

	public CacheControl getControl() {
		return control;
	}

	public boolean isExpired() {
		Date now = new Date();
		if (control.getMaxAge().before(now)) {
			return true;
		} else if (expires != null && control.getMaxAge() != null) {
			return expires.before(now);
		} else {
			return false;
		}
	}

}
