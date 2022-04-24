import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
		
	public void generateRSA(int keySize) throws NoSuchAlgorithmException, IOException {
				 
		Scanner objScanner = new Scanner(System.in);
				
		KeyPairGenerator objKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		objKeyPairGenerator.initialize(keySize);
		KeyPair objKeyPair = objKeyPairGenerator.genKeyPair();
		
		//
		
		byte[] objX509PublicKey = objKeyPair.getPublic().getEncoded();
		byte[] objPKCS8PrivateKey = objKeyPair.getPrivate().getEncoded();
		
		//
		
		System.out.println("");
		System.out.println("We're now creating a new pair of " + keySize + "-bit assymetric keys using the RSA algorithm.");
		System.out.println("");
		System.out.println("Where do you want to save your public and private key?\n(e.g. /home/user/Documents)");
		String objPath = objScanner.nextLine(); // <--
		System.out.println("");
		System.out.println("Now, give your new pair of keys a nice name!");
		String objFileName = objScanner.nextLine(); // <--
		File publicKeyFile = new File(objPath + "/" + objFileName + "_public");
		File privateKeyFile = new File(objPath + "/" + objFileName + "_private");
				
		FileOutputStream objFOSPublic = new FileOutputStream(publicKeyFile);
		objFOSPublic.write(objX509PublicKey);
		objFOSPublic.close();
		
		FileOutputStream objFOSPrivate = new FileOutputStream(privateKeyFile);
		objFOSPrivate.write(objPKCS8PrivateKey);
		objFOSPrivate.close();
		
		System.out.println("");
		System.out.println("You have successfully created a new pair of keys!");
		System.out.println("");
		System.out.println("Take a look at " + "'" + objPath + "'");
		
		
	}
	
	public void publicEncryptRSA(int keySize) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		//
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		ByteArrayOutputStream objByteArrayOutputStream = new ByteArrayOutputStream();
		
		System.out.println("");
		System.out.println("We're now starting a new encryption using the RSA algorithm with a " + keySize + "-bit public key.");
		System.out.println("");
		System.out.println("First, you need to show us where is the " + keySize + "-bit public key.\n(e.g. /home/user/Documents/john_public)");
		File publicKeyFile = new File(objScanner.nextLine()); // <--
		FileInputStream FISPublicKeyFile = new FileInputStream(publicKeyFile);
		
		int curByte = 0;
		while ((curByte = FISPublicKeyFile.read()) != -1) {
			
		objByteArrayOutputStream.write(curByte);
			
		}
		
		X509EncodedKeySpec objX509PublicKey = new X509EncodedKeySpec(objByteArrayOutputStream.toByteArray());
		
		objByteArrayOutputStream.close();
		
		//
		
		KeyFactory objKeyFactory = KeyFactory.getInstance("RSA");
		PublicKey objPublicKey = objKeyFactory.generatePublic(objX509PublicKey);
		
		//
		
		Cipher objCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		objCipher.init(Cipher.ENCRYPT_MODE, objPublicKey);
		
		//
		
		System.out.println("");
		System.out.println("Now, you just need to type the information you want encrypted by the public key.");
		String inputString = objScanner.nextLine(); // <--
		byte[] byteString = objCipher.doFinal(Base64.getDecoder().decode(inputString));
		String base64String = Base64.getEncoder().encodeToString(byteString);
		
		//
		
		System.out.println("");
		System.out.println("Here's the string encrypted:");
		System.out.println(base64String);
		System.out.println("");
		System.out.println("Anyone trying to decipher this message will need the private key paired with the public key you used.");
		
	}
	
	public void privateDecryptRSA(int keySize) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		ByteArrayOutputStream objByteArrayOutputStream = new ByteArrayOutputStream();
		
		System.out.println("");
		System.out.println("We're now starting the decryption of a string encrypted using the RSA algorithm with a " + keySize + "-bit public key.");
		System.out.println("");
		System.out.println("First, we need the path to the private key paired with the public key used in encryption.\n(e.g. /home/user/Documents/john_private)");
		File objFilePrivateKey = new File(objScanner.nextLine()); // <--
		FileInputStream objFISPrivateKeyFile = new FileInputStream(objFilePrivateKey);
		
		int curByte = 0;
		while ((curByte = objFISPrivateKeyFile.read()) != -1) {
			
			objByteArrayOutputStream.write(curByte);
			
		}
		
		PKCS8EncodedKeySpec objPKCS8PrivateKey = new PKCS8EncodedKeySpec(objByteArrayOutputStream.toByteArray());
		
		objByteArrayOutputStream.close();
		
		//
		
		KeyFactory objKeyFactory = KeyFactory.getInstance("RSA");
		PrivateKey objPrivateKey = objKeyFactory.generatePrivate(objPKCS8PrivateKey);
		
		//
		
		Cipher objCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		objCipher.init(Cipher.DECRYPT_MODE, objPrivateKey);
		
		//
		
		System.out.println("");
		System.out.println("Now, you just need to show us the ciphered message you want decrypted.");
		String inputString = objScanner.nextLine(); // <--
		byte[] byteString = objCipher.doFinal(Base64.getDecoder().decode(inputString));
		String base64String = Base64.getEncoder().encodeToString(byteString);
		
		//
		
		System.out.println("");
		System.out.println("Here's the string decrypted:");
		System.out.println(base64String);
		
	}
	
	public void privateEncryptRSA(int keySize) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		ByteArrayOutputStream objByteArrayOutputStream = new ByteArrayOutputStream();
		
		System.out.println("");
		System.out.println("We're now starting a new encryption using the RSA algorithm with a " + keySize + "-bit private key.");
		System.out.println("");
		System.out.println("First, we need the path to to your private key.\n(e.g. /home/user/Documents/john_private)");
		File objFilePrivateKey = new File(objScanner.nextLine()); // <--
		FileInputStream objFISPrivateKeyFile = new FileInputStream(objFilePrivateKey);
		
		int curByte = 0;
		while ((curByte = objFISPrivateKeyFile.read()) != -1) {
			
			objByteArrayOutputStream.write(curByte);
			
		}
		
		PKCS8EncodedKeySpec objPKCS8PrivateKey = new PKCS8EncodedKeySpec(objByteArrayOutputStream.toByteArray());
		
		objByteArrayOutputStream.close();
		
		//
		
		KeyFactory objKeyFactory = KeyFactory.getInstance("RSA");
		PrivateKey objPrivateKey = objKeyFactory.generatePrivate(objPKCS8PrivateKey);
		
		//
		
		Cipher objCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		objCipher.init(Cipher.ENCRYPT_MODE, objPrivateKey);
		
		//
		
		System.out.println("");
		System.out.println("Now, you just need to type the information you want encrypted by the private key.");
		String inputString = objScanner.nextLine(); // <--
		byte[] byteString = objCipher.doFinal(Base64.getDecoder().decode(inputString));
		String base64String = Base64.getEncoder().encodeToString(byteString);
		
		//
		
		System.out.println("");
		System.out.println("Here's the string encrypted:");
		System.out.println(base64String);
		
	}
	
	public void publicDecryptRSA(int keySize) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IOException {
		
//
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		ByteArrayOutputStream objByteArrayOutputStream = new ByteArrayOutputStream();
		
		System.out.println("");
		System.out.println("We're now starting the decryption of a string encrypted using the RSA algorithm with a " + keySize + "-bit private key.");
		System.out.println("");
		System.out.println("First, you need to show us where is the " + keySize + "-bit public key. \n(e.g. /home/user/Documents/john_public)");
		File publicKeyFile = new File(objScanner.nextLine()); // <--
		FileInputStream FISPublicKeyFile = new FileInputStream(publicKeyFile);
		
		int curByte = 0;
		while ((curByte = FISPublicKeyFile.read()) != -1) {
			
		objByteArrayOutputStream.write(curByte);
			
		}
		
		X509EncodedKeySpec objX509PublicKey = new X509EncodedKeySpec(objByteArrayOutputStream.toByteArray());
		
		objByteArrayOutputStream.close();
		
		//
		
		KeyFactory objKeyFactory = KeyFactory.getInstance("RSA");
		PublicKey objPublicKey = objKeyFactory.generatePublic(objX509PublicKey);
		
		//
		
		Cipher objCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		objCipher.init(Cipher.DECRYPT_MODE, objPublicKey);
		
		//
		
		System.out.println("");
		System.out.println("Now, you just need to show us the ciphered message you want decrypted.");
		String inputString = objScanner.nextLine(); // <--
		byte[] byteString = objCipher.doFinal(Base64.getDecoder().decode(inputString));
		String base64String = Base64.getEncoder().encodeToString(byteString);
		
		//
		
		System.out.println("");
		System.out.println("Here's the string decrypted:");
		System.out.println(base64String);
		System.out.println("");
		
	}

}
