package learnJavaNetProgam;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 构造函数2个参数 hostname 和 port 尝试使用 hostname的代理服务器完成所有的HTTP链接（else if可扩展）
 * 除非这个代理服务器之前未能成功解析与一个特定URL的链接 那么会建议使用直接连接
 * 
 * 每个虚拟机都只有一个ProxySelector，如需要改变这个选择器，需要吧新的选择器传给静态方法ProxySelecter.setDAefault()
 * ProxySelector selector = new LocalProxySelector();
 * ProxySelector.setDefault(selector);
 * 
 * 一般不要在共享环境下用这个，比如不要再servlet里面改变ProxySelector，因为这样会改变同一个容器中运行的所有servlet的ProxySelector
 * 
 * @author chenhaoyu
 *
 */
public class LocalProxySelector extends ProxySelector {

	SocketAddress proxyAddress;

	private List<URI> failed = new ArrayList<URI>();

	public LocalProxySelector(String hostname, int port) {
		proxyAddress = new InetSocketAddress(hostname, port);
	}

	@Override
	public List<Proxy> select(URI uri) {
		List<Proxy> result = new ArrayList<>();

		if (failed.contains(uri) || !"http".equalsIgnoreCase(uri.getScheme())) {
			result.add(Proxy.NO_PROXY);
		} else {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
			result.add(proxy);
		}

		return result;

	}

	@Override
	public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
		failed.add(uri);
	}

}
