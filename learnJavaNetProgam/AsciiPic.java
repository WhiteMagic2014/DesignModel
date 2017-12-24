package learnJavaNetProgam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.imageio.ImageIO;

public class AsciiPic {

	public static void main(String[] args) {

		File file = new File("/Users/chenhaoyu/Desktop/fgo/testpic");
		OutputStream out = null;
		Writer writer = null;
		try {
			out = new FileOutputStream(file);
			writer = new OutputStreamWriter(out);
			byte[] pic=createAsciiPic("/Users/chenhaoyu/Desktop/fgo/nilu.jpg");
			// createAsciiPic(null);
			out.write(pic);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static byte[] createAsciiPic(String path) {
		if (path == null) {
			path = "/Users/chenhaoyu/Desktop/fgo/nilu.jpg";
		}
		StringBuilder sb = new StringBuilder();
		final String base = "@#&$%*o!;.";// 字符串由复杂到简单
		try {
			final BufferedImage image = ImageIO.read(new File(path));
			for (int y = 0; y < image.getHeight(); y += 5) {
				for (int x = 0; x < image.getWidth(); x += 3) {
					final int pixel = image.getRGB(x, y);
					final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
					final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
					final int index = Math.round(gray * (base.length() + 1) / 255);
					// System.out.print(index >= base.length() ? " " :
					// String.valueOf(base.charAt(index)));
					sb.append(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
				}
				// System.out.println();
				sb.append("\n");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return sb.toString().getBytes();
	}
}
