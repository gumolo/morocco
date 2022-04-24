import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Scanner;

public class SHA {
	
	private static String bytesToHex(byte[] hash) {
		
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		
		return hexString.toString();
				
	}
	
	public void fileToHash(String algorithm) throws IOException, NoSuchAlgorithmException {
		
		Scanner objScanner = new Scanner(System.in);
		
		System.out.println("");
		System.out.println("We're now starting the digest of a file using the " + algorithm + " algorithm.");
		System.out.println("");
		System.out.println("You just need to specify the path to the file you want digested.\n(e.g. /home/user/Documents/document.pdf)");
		File objFile = new File(objScanner.nextLine());
		FileInputStream objFileInputStream = new FileInputStream(objFile);
		
		MessageDigest objMessageDigest = MessageDigest.getInstance(algorithm);
		
		byte[] objByteArray = new byte[1024];
		int bytesCount = 0;
		
		while((bytesCount = objFileInputStream.read(objByteArray)) != -1) {
			
			objMessageDigest.update(objByteArray, 0, bytesCount);
			
		}
				
		byte[] objDigest = objMessageDigest.digest();
				
		System.out.println(bytesToHex(objDigest));
				
	}
	
	public void stringToHash(String algorithm) throws NoSuchAlgorithmException {
		
		Scanner objScanner = new Scanner(System.in);
		System.out.println("");
		System.out.println("We're now starting the digest of a string using the " + algorithm + " algorithm.");
		System.out.println("");
		System.out.println("You just need to write anything you want to hash.");
		String objMessageInput = objScanner.nextLine();
		byte[] objMessageByte = objMessageInput.getBytes();
		
		MessageDigest objMessageDigest = MessageDigest.getInstance(algorithm);
								
		byte[] objDigest = objMessageDigest.digest(objMessageByte);
				
		System.out.println(bytesToHex(objDigest));
				
	}
	
}
