import java.security.SecureRandom;
import java.util.Scanner;

public class PSWGEN {

	public static void passwordGenerator(int keySize, int keyQuantity) {
		
		System.out.println("");
		SecureRandom objSecureRandom = new SecureRandom();
		String objChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&'()*+,-./:;<=>?@[]^_`{}~";
		StringBuilder objStringBuilder = new StringBuilder();
				
		for (int i = 0; i < keySize; i++) {
			
            int randomIndex = objSecureRandom.nextInt(objChars.length());
            objStringBuilder.append(objChars.charAt(randomIndex));
        }
			
		if (keyQuantity > 0) {
			 System.out.println(objStringBuilder);
			 passwordGenerator(keySize, keyQuantity-1);
		}
         		
	}
				
}