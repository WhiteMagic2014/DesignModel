package learnIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Simulator {

	public static void main(String[] args) {

		File wjjFile = new File("/Users/chenhaoyu/Downloads/learnIO");
		if (wjjFile.isDirectory()) {
			System.out.println("文件夹已经存在");
		} else {
			wjjFile.mkdir();// 创建文件夹
		}

		File file = new File(wjjFile.getAbsolutePath() + "/magic.txt");
		if (file.exists()) {
			System.out.println("已经存在");
		} else {
			try {
				file.createNewFile();// 创建文件
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.println("path:" + file.getAbsolutePath());
		System.out.println("big:" + file.length());

		Magicfind magicfind = new Magicfind();

		File magicFile = new File("/Users/chenhaoyu/Downloads/learnIO/magic1122.txt");
		// magicfind.find(magicFile);
		FileInputStream fins = null;
		FileOutputStream fous = null;

		// 从文件中读取
		// 因为File没有读写的能力，所以用inputstream来读取，inputstream是字节流，可以操作任何对象
		try {
			fins = new FileInputStream(magicFile);
			// 定义一个字节数组
			byte[] bytes = new byte[1024];// 相当于缓存
			int n = 0;// 得到实际读取到的字节数
			// 循环读取
			while ((n = fins.read(bytes)) != -1) {
				String info = new String(bytes, 0, n);
				System.out.println(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fins.close();// 文件的关闭必须在finally中，不管有没有异常都会执行
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		try {
			fous = new FileOutputStream(magicFile);
			String name = "whitemagic";
			
			fous.write(name.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fous.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}

class Magicfind {
	// 查询一个文件夹下所有文件
	public void find(File mFile) {
		if (mFile.isDirectory()) {
			File listFiles[] = mFile.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				System.out.println(listFiles[i].getName());
				if (listFiles[i].isDirectory()) {
					find(new File(listFiles[i].getAbsolutePath()));
				}
			}
		}
	}
}
