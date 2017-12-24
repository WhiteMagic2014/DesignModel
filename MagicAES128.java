
import java.util.Formatter;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MagicAES128 {

	// 加密秘钥 ，16个字节也就是128 bit
	private static final byte[] AES_KEY = { 90,105,98,84,76,74,111,80,74,74,49,100,115,76,118,85};

	// 需要加密的数据(保证16个字节，不够的自己填充)
	// private static byte[] SOURCE_BUF = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
	// 12, 13, 14, 15, 16};

	public static void main(String args[]) {
		
		
		 byte[] info = getRandomByte();
	        info[0] = 0x06;
	        info[1] = 0x01;
	        info[2] = 0x01;
	        info[3] = 0x01;
		

		byte[] yuan1 = { 0x06, 0x01, 0x01, 0x01, 0x2d, 0x1a, 0x68, 0x3d, 0x48, 0x27, 0x1a, 0x18, 0x31, 0x6e, 0x47,
				0x1a };

		byte[] yuan2 = { (byte) 0xa5, 0x6c, 0x7d, 0x75, 0x48, (byte) 0xde, (byte) 0xff, (byte) 0xef, (byte) 0xe7,
				(byte) 0xac, (byte) 0x1e, (byte) 0xa9, (byte) 0xbc, (byte) 0xce, 0x66, (byte) 0xe6 };

		try {
			
			
			System.out.println("\n 加密前   " + BytetohexString(info));

			System.out.println("\n 加密后   " + BytetohexString(encrypt(info)));

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 加密方法
	private static byte[] encrypt(byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);

		// System.out.println("加密___" + BytetohexString(encrypted));
		return encrypted;
	}

	// 解密方法
	private static byte[] decrypt(byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(AES_KEY, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);

		// System.out.println("解密___" + BytetohexString(decrypted));
		return decrypted;
	}
	
	
	 public static byte[] getRandomByte() {
	        byte[] b = new byte[16];
	        Random random = new Random();

	        for (int i = 0; i < 15; i++) {
	            Integer is = random.nextInt(127);
	            b[i] = Byte.parseByte(is.toString());
	        	
//	        	b[i]= 0x00;
	        }

	        return b;
	    }

	private static String BytetohexString(byte[] b) {
		int len = b.length;
		StringBuilder sb = new StringBuilder(b.length * (2 + 1));
		Formatter formatter = new Formatter(sb);

		for (int i = 0; i < len; i++) {
			if (i < len - 1)
				formatter.format("0x%02X  ", b[i]);
			else
				formatter.format("0x%02X", b[i]);
		}
		formatter.close();
		return sb.toString();
	}
	


}
