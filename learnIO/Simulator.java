package learnIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Simulator {

	public static void main(String[] args) {

		// 1.在指定目录创建文件夹
		File wjjFile = new File("/Users/chenhaoyu/Downloads/learnIO");
		if (wjjFile.isDirectory()) {
			System.out.println("文件夹已经存在");
		} else {
			wjjFile.mkdir();// 创建文件夹
		}

		// 2.在指定目录创建文件
		File file = new File(wjjFile.getAbsolutePath() + "/magic.txt");
		if (file.exists()) {
			System.out.println("magic.txt已经存在");
		} else {
			try {
				file.createNewFile();// 创建文件
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("path:" + file.getAbsolutePath());
		System.out.println("big:" + file.length());

		// 3.输出目录下所有的文件
		Magicfind magicfind = new Magicfind();
		File findMagicFile = new File("/Users/chenhaoyu/Downloads/learnIO");
		// magicfind.find(findMagicFile);

		// 4.文件的读取与写入

		// 字节流 操作单位-byte
		FileInputStream fins = null;
		FileOutputStream fous = null;
		File magicFile = new File("/Users/chenhaoyu/Downloads/learnIO/magic字节流.txt");
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
				// System.out.println(info);
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

		// 输出到文件
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

		// 字符流 操作单位-char
		FileReader fr = null;// 相当于输入流
		FileWriter fw = null;// 相当于输出流
		File magicFile1 = new File("/Users/chenhaoyu/Downloads/learnIO/magic字符流读取.txt");
		File magicFile2 = new File("/Users/chenhaoyu/Downloads/learnIO/magic字符流输出.txt");

		try {
			// 创建对象
			fr = new FileReader(magicFile1);
			fw = new FileWriter(magicFile2);
			// 文件读到内存
			char c[] = new char[1024];
			int n = 0;
			while ((n = fr.read(c)) != -1) {

				// 写到文件
				// String s = new String(c, 0, n);
				// System.out.println(s);
				fw.write(c, 0, n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 缓冲字符流 操作单位-String 操作一行readline
		File magicFile_buffer = new File("/Users/chenhaoyu/Downloads/learnIO/magic缓存字符流读取.txt");
		File magicFile_buffer1 = new File("/Users/chenhaoyu/Downloads/learnIO/magic缓存字符流输出.txt");

		BufferedReader bReader = null;
		BufferedWriter bWriter = null;
		try {

			FileReader fReader = new FileReader(magicFile_buffer);
			FileWriter fWriter = new FileWriter(magicFile_buffer1);
			// 当然可以直接写成下面这样，因为 FileReader，FileWriter需要的参数可以有3种，上面的字符，字节流也一样
			// FileReader fileReader = new
			// FileReader("/Users/chenhaoyu/Downloads/learnIO/magic缓存字符流读取.txt");

			bReader = new BufferedReader(fReader);
			bWriter = new BufferedWriter(fWriter);

			String s = null;
			while ((s = bReader.readLine()) != null) {
				// System.out.println(s);

				bWriter.write(s + "\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
				bWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}// psvm结束

}// class结束

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
