package magicThread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenhaoyu
 * 获取文件摘要
 */
public class ReturnDigest extends Thread {

	private byte[] digest;
	private String filename;

	public ReturnDigest(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		super.run();

		try {
			FileInputStream fin = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(fin, sha);

			while (din.read() != -1)
				;
			din.close();
			digest = sha.digest();

			ReturnDigestUserInterface.receiveDigest(digest, filename);
			
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (IOException eio) {
			eio.printStackTrace();
		}

	}

	public byte[] getDigest() {
		return digest;
	}

}
