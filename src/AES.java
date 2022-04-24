import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	public void encryptAES(int keySize) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		KeyGenerator objKeyGen = KeyGenerator.getInstance("AES");
		objKeyGen.init(keySize);
		SecretKey objSecretKey = objKeyGen.generateKey();
		String objSecretKeyBASE64 = Base64.getEncoder().encodeToString(objSecretKey.getEncoded());
		
		//
		
		Cipher objCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		objCipher.init(Cipher.ENCRYPT_MODE, objSecretKey);
		String objIVBASE64 = Base64.getEncoder().encodeToString(objCipher.getIV());
		
		//
		System.out.println("");
		System.out.println("We're now starting a new encryption using the AES algorithm with a " + keySize + "-bit symmetric key.");
		System.out.println("");
		System.out.println("First, you need to specify the path to the file you want encrypted.\n(e.g. /home/user/Documents/regularFile.png)\nNOTE: You must specify the extension of your file!");
		String objInputPath = objScanner.nextLine(); // <--
		System.out.println("Now tell us: where do you want to save your encrypted file?\n(e.g. /home/user/Documents)");
		String objOutputPath = objScanner.nextLine(); // <--
		System.out.println("To complete, give your encrypted file a name. No need for an extension.");
		String objOutputPathFileName = objScanner.nextLine(); // <--
		
		File inputFile = new File(objInputPath);
		File outputFile = new File(objOutputPath + "/" + objOutputPathFileName);
		
		FileInputStream fileInputStream = new FileInputStream(inputFile);
		CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, objCipher);
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		
		byte[] finalCipherTextBytes = new byte[16];
		int i = 0;
		
		while((i = cipherInputStream.read(finalCipherTextBytes)) != -1) {
			
			fileOutputStream.write(finalCipherTextBytes, 0, i);
	
		}
		
		System.out.println("");
		System.out.println("You have successfully encrypted your file!");
		System.out.println("");
		System.out.println("Here's the key to decrypt your file:\n" + objSecretKeyBASE64);
		System.out.println("");
		System.out.println("And here's the initialization vector that was used:\n" + objIVBASE64);
		System.out.println("");
		System.out.println("Don't lose this information, there is no way to recover this later.");
		System.out.println("");
		
	}
	
	public void decryptAES(int keySize) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		System.out.println("");
		System.out.println("We're now starting the decryption of a file encrypted using the AES algorithm with a " + keySize + "-bit symmetric key.");
		System.out.println("");
		System.out.println("First, we need the secret key given to you after encryption.");
		String objSecretKeyBASE64 = objScanner.nextLine(); // <--
		byte[] objKeyRAW = Base64.getDecoder().decode(objSecretKeyBASE64);
		
		//
		
		System.out.println("");
		System.out.println("Now, we need the initialization vector. You also got this in the end of encryption process.");
		String objIVBASE64 = objScanner.nextLine(); // <--
		byte[] objIVRAW = Base64.getDecoder().decode(objIVBASE64);
		
		//
		
		SecretKeySpec objSecretKeySpec = new SecretKeySpec(objKeyRAW, "AES");
		IvParameterSpec objIVSpec = new IvParameterSpec(objIVRAW);
		
		//
		
		Cipher objCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		objCipher.init(Cipher.DECRYPT_MODE, objSecretKeySpec, objIVSpec);
		
		//
		
		System.out.println("");
		System.out.println("Show us the path for your encrypted file in your computer.\n(e.g. /home/user/encryptedFile)");
		String objInputPath = objScanner.nextLine(); // <--
		System.out.println("");
		System.out.println("Now you can tell us where do you want to save you decrypted file.\n(e.g. /home/user/Documents)");
		String objOutputPath = objScanner.nextLine(); // <--
		System.out.println("");
		System.out.println("To complete, give your decrypted file a name.\n(e.g. /home/user/Documents/decryptedFile.png)\nNOTE: You need to specify the extension for your file!");
		String objOutputPathFile = objScanner.nextLine(); // <--
		
		File inputFile = new File(objInputPath);
		File outputFile = new File(objOutputPath + "/" + objOutputPathFile);
		
		FileInputStream fileInputStream = new FileInputStream(inputFile);
		CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, objCipher);
		FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
		
		byte[] finalCipherTextBytes = new byte[16];
		int i = 0;
		
		while((i = cipherInputStream.read(finalCipherTextBytes)) != -1) {
			
			fileOutputStream.write(finalCipherTextBytes, 0, i);
	
		}
		
		System.out.println("");
		System.out.println("You have successfully decrypted your file!");
		System.out.println("");
		System.out.println("Take a look at " + "'" + objOutputPath + "'");
		
	}

}
