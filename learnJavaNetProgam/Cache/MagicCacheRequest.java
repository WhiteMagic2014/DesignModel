package learnJavaNetProgam.Cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

/**
 * 一个基本的CacheRequest子类，会传回一个ByteArrayOutputStream,然后可以用getdata方法获取数据
 * 这里只获取了outputstream中写入的数据，可以进行自定义的修改，比如用FileOutStream把结果存到文件中
 * 
 * 
 * @author chenhaoyu
 *
 */
public class MagicCacheRequest extends CacheRequest {

	private ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Override
	public OutputStream getBody() throws IOException {
		return out;
	}

	@Override
	public void abort() {
		out.reset();
	}

	public byte[] getData() {
		if (out.size() == 0) {
			return null;
		} else {
			return out.toByteArray();
		}
	}

}
