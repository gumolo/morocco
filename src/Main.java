import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidAlgorithmParameterException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, SignatureException, NoSuchProviderException {
		
		System.out.println("");
		System.out.println(" ,--,--,--. ,---. ,--.--. ,---.  ,---. ,---. ,---.  ");
		System.out.println(" |        || .-. ||  .--'| .-. || .--'| .--'| .-. | ");
		System.out.println(" |  |  |  |' '-' '|  |   ' '-' '  `--.  `--.' '-' ' ");
		System.out.println(" `--`--`--' `---' `--'    `---'  `---' `---' `---'  ");
		System.out.println("");
		System.out.println("-----------------------------------------------------");
		System.out.println("  Written in Java                     version x.x.x  ");
		System.out.println("-----------------------------------------------------");
		System.out.println("");
		System.out.println(" • Rivest–Shamir–Adleman (RSA)");
		System.out.println("");
		System.out.println("   ‣ newkeypair [keySize]                      Creates an asymmetric RSA keypair");
		System.out.println("                                               of 1024, 2048 or 4096-bit.");
		System.out.println("");
		System.out.println("   ‣ encryptrsa [public|private] [keySize]     Encrypts a string using RSA algorithm");
		System.out.println("                                               and a 1024, 2048 or 4096-bit public or");
		System.out.println("                                               private key.");
		System.out.println("");
		System.out.println("   ‣ decryptrsa [public|private] [keySize]     Decrypts a RSA-encrypted string using a");
		System.out.println("                                               1024, 2048or 4096-bit public or private");
		System.out.println("                                               key.");
		System.out.println("");
		System.out.println(" • Advanced Encryption Standard (AES/Rijndael)");
		System.out.println("");
		System.out.println("   ‣ encryptaes [keySize]                      Encrypts a file using AES algorithm and");
		System.out.println("                                               a 128 or 256-bit symmetric key.");
		System.out.println("");
		System.out.println("   ‣ decryptaes [keySize]                      Decrypts a file encrypted using AES");
		System.out.println("                                               algorithm with a 128 or 256-bit symmetric");
		System.out.println("                                               key.");
		System.out.println("");
		System.out.println(" • Message Digest Algorithms");
		System.out.println("");
		System.out.println("   ‣ md5 [file|string]                         Applies the MD5 algorithm to a file or");
		System.out.println("                                               string and returns his digest.");
		System.out.println("");
		System.out.println("   ‣ sha256 [file|string]                      Applies the SHA-256 algorithm to a file");
		System.out.println("                                               or string and returns his digest.");
		System.out.println("");
		System.out.println("   ‣ sha512 [file|string]                      Applies the SHA-512 algorithm to a file");
		System.out.println("                                               or string and returns his digest.");
		System.out.println("");
		System.out.println("   ‣ sha3256 [file|string]                     Applies the SHA3-256 algorithm to a file");
		System.out.println("                                               or string and returns his digest.");
		System.out.println("");
		System.out.println("   ‣ sha3512 [file|string]                     Applies the SHA3-512 algorithm to a file");
		System.out.println("                                               or string and returns his digest.");
		System.out.println("");
		System.out.println(" • Miscellaneous");
		System.out.println("");
		System.out.println("   ‣ pswgen                                    Generates a certain number of lenght specified");
		System.out.println("                                               pseudorandom lines using lower and uppercase");
		System.out.println("                                               letters, numbers and special characters");
		System.out.println("                                               that can be used as a secure password.");
		System.out.println("");
		
		//
		
		AES objAES = new AES();
		RSA objRSA = new RSA();
		SHA objSHA = new SHA();
		PSWGEN objMisc = new PSWGEN();
		
		Scanner objScanner = new Scanner(System.in);
		
		//
		
		String menuOption = objScanner.nextLine();
		
		//
		
		switch (menuOption) {
		
		default:
			System.out.println("There is no such command named " + "'" + menuOption + "'" + ".");
			break;
			
		case "newkeypair 1024":
			objRSA.generateRSA(1024);
			break;
			
		case "newkeypair 2048":
			objRSA.generateRSA(2048);
			break;
				
		case "newkeypair 4096":
			objRSA.generateRSA(4096);
			break;
							
		case "encryptrsa public 1024":
			objRSA.publicEncryptRSA(1024);
			break;
				
		case "encryptrsa private 1024":
			objRSA.privateEncryptRSA(1024);
			break;
								
		case "encryptrsa public 2048":
			objRSA.publicEncryptRSA(2048);
			break;
				
		case "encryptrsa private 2048":
			objRSA.privateDecryptRSA(2048);
			break;
				
		case "encryptrsa public 4096":
			objRSA.publicEncryptRSA(4096);
			break;
				
		case "encryptrsa private 4096":
			objRSA.privateEncryptRSA(4096);
			break;
				
		case "decryptrsa private 1024":
			objRSA.privateDecryptRSA(1024);
			break;
				
		case "decryptrsa public 1024":
			objRSA.publicDecryptRSA(1024);
			break;
				
		case "decryptrsa private 2048":
			objRSA.privateDecryptRSA(2048);
			break;
				
		case "decryptrsa public 2048":
			objRSA.publicDecryptRSA(2048);
			break;
				
		case "decryptrsa private 4096":
			objRSA.privateDecryptRSA(4096);
			break;
				
		case "decryptrsa public 4096":
			objRSA.publicDecryptRSA(4096);
			break;
			
		case "encryptaes 128":
			objAES.encryptAES(128);
			break;
			
		case "encryptaes 256":
			objAES.encryptAES(256);
			break;
			
		case "decryptaes 128":
			objAES.decryptAES(128);
			break;
			
		case "decryptaes 256":
			objAES.decryptAES(256);
			break;
			
		case "md5 file":
			objSHA.fileToHash("MD5");
			break;
			
		case "sha256 file":
			objSHA.fileToHash("SHA-256");
			break;
			
		case "sha512 file":
			objSHA.fileToHash("SHA-512");
			break;
			
		case "sha3256 file":
			objSHA.fileToHash("SHA3-256");
			break;
			
		case "sha3512 file":
			objSHA.fileToHash("SHA3-512");
			break;
			
		case "md5 string":
			objSHA.stringToHash("MD5");
			break;
			
		case "sha256 string":
			objSHA.stringToHash("SHA-256");
			break;
			
		case "sha512 string":
			objSHA.stringToHash("SHA-512");
			break;
			
		case "sha3256 string":
			objSHA.stringToHash("SHA3-256");
			break;
			
		case "sha3512 string":
			objSHA.stringToHash("SHA3-512");
			break;
			
		case "pswgen":
			System.out.println("You're now generating your pseudorandom password.");
			System.out.println("First, you need to specify a length for your password.");
			int pswgenKeySize = objScanner.nextInt();
			System.out.println("Now, you need to specify the number of passwords you want.");
			int pswgenKeyQuantity = objScanner.nextInt();
			objMisc.passwordGenerator(pswgenKeySize, pswgenKeyQuantity);
			System.out.println("Done! You successfully generated all " + pswgenKeyQuantity + " passwords.");
			break;
									
		}
		
	}

}
