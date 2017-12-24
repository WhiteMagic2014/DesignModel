package Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Magic {

	public static String str = "11223344";

	public Magic() throws NoSuchAlgorithmException, InvalidKeySpecException {

		// Q: Key key = keyGen.generateKey();产生的是一个随机密钥吗？
		// A: 是的

		// Q: 如果是的，那我想在另一个程序中解密有这个随机密钥生成的密文，岂不是不能直接获得密钥吗？
		// A: Generate a DES key and change it to raw data
		KeyGenerator desGen = KeyGenerator.getInstance("DES");
		SecretKey desKey = desGen.generateKey();
		SecretKeyFactory desFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec desSpec = (DESKeySpec) desFactory.getKeySpec(desKey, javax.crypto.spec.DESKeySpec.class);
		byte[] rawDesKey = desSpec.getKey();

		// Q: 我想用“12345678”来作为密钥来加密和解密该怎样写？
		// A: 可以用自定义的string获得SecretKey
		String keyString = "12345678";//但是密码长度必须是8byte的倍数
		byte[] keyData = keyString.getBytes();
		SecretKey myDesKey = new SecretKeySpec(keyData, "DES");

		// Q: 如果在程序中用指定的密钥来例如“12345678”加密进行加密和解密，
		// 那么别人不就可以反编class文件来看到你的使用的加密算法和密钥，岂不是很不安全？
		// A: read the key string from a file or command line parameter

	}

}
