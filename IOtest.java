import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOtest {

	public static void main(String args[]) {

		String filename = "/Users/chenhaoyu/Downloads/暗黑之毁灭.txt";
		System.out.println("测试仅测试读取时间，不计算输出时间");
		System.out.println("测试对象：62mb文本文件");
		
		System.out.println("字节流");
		ioA(filename);
		ioB(filename);
		System.out.println("字符流");
		ioC(filename);
		ioD(filename);
	}


	
	/**
	 * InputStream
	 * 
	 * @param filename
	 */
	private static void ioA(String filename) {
		try (InputStream in = new FileInputStream(filename)) {

			long startTime = System.currentTimeMillis(); // 获取开始时间

			// 定义一个字节数组
			byte[] bytes = new byte[1024];// 相当于缓存
			int n = 0;// 得到实际读取到的字节数
			while ((n = in.read(bytes)) != -1) {
				// String info = new String(bytes, 0, n);
				// System.out.println(info);

			}

			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("InputStream 运行时间：" + (endTime - startTime) + "ms");

		} catch (FileNotFoundException e) {
			System.err.println(e + "找不到文件");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * InputStream —> BufferedInputStream
	 * 
	 * @param filename
	 */
	private static void ioB(String filename) {
		try (InputStream in = new BufferedInputStream(new FileInputStream(filename))) {

			long startTime = System.currentTimeMillis(); // 获取开始时间

			// 定义一个字节数组
			byte[] bytes = new byte[1024];// 相当于缓存
			int n = 0;// 得到实际读取到的字节数
			while ((n = in.read(bytes)) != -1) {
				// String info = new String(bytes, 0, n);
				// System.out.println(info);

			}

			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("InputStream —> BufferedInputStream运行时间：" + (endTime - startTime) + "ms");

		} catch (FileNotFoundException e) {
			System.err.println(e + "找不到文件");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	

	/**
	 * InputStream —> BufferedInputStream —>InputStreamReader
	 * 
	 * @param filename
	 */
	private static void ioC(String filename) {
		try (InputStream in = new BufferedInputStream(new FileInputStream(filename))) {
			Reader r = new InputStreamReader(in);

			long startTime = System.currentTimeMillis(); // 获取开始时间

			int c;
			while ((c = r.read()) != -1) {
				// System.out.print((char) c);
			}

			long endTime = System.currentTimeMillis(); // 获取结束时间

			System.out.println(
					"InputStream —> BufferedInputStream —>InputStreamReader运行时间：" + (endTime - startTime) + "ms");

		} catch (FileNotFoundException e) {
			System.err.println(e + "找不到文件");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * InputStream —>InputStreamReader —>BufferedReader
	 * 
	 * @param filename
	 */
	private static void ioD(String filename) {
		try (Reader r = new InputStreamReader(new FileInputStream(filename))) {

			BufferedReader br = new BufferedReader(r);

			String s = null;

			long startTime = System.currentTimeMillis(); // 获取开始时间

			while ((s = br.readLine()) != null) {
				// System.out.println(s);
			}

			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("InputStream —> InputStreamReader —>BufferedReader运行时间：" + (endTime - startTime) + "ms");

		} catch (FileNotFoundException e) {
			System.err.println(e + "找不到文件");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
