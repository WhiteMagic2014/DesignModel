package magicThread;

import javax.xml.bind.DatatypeConverter;

public class ReturnDigestUserInterface {

	public static void main(String[] args) {
		for (String filename : args) {
			ReturnDigest digestThread = new ReturnDigest(filename);
			digestThread.start();
		}
	}
	
	
	public static void receiveDigest(byte[] digest,String name){
		StringBuilder result = new StringBuilder(name);
		result.append(": ");
		result.append(DatatypeConverter.printHexBinary(digest));
		System.out.println(result);
		
	}

}
