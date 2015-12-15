package sciencefair.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class MD5 {

public static void main(String[] args) {

		        String passwordToHash = "password";
		        String salt;
				try {
					salt = getSalt();

		         
		        String securePassword = getSecurePassword(passwordToHash, salt);
		        System.out.println(securePassword); //Prints 83ee5baeea20b6c21635e4ea67847f66
		         
		        String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);
		        System.out.println(regeneratedPassowrdToVerify); //Prints 83ee5baeea20b6c21635e4ea67847f66
		        
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		     
		    private static String getSecurePassword(String passwordToHash, String salt)
		    {
		        String generatedPassword = null;
		        try {
		            // Create MessageDigest instance for MD5
		            MessageDigest md = MessageDigest.getInstance("MD5");
		            //Add password bytes to digest
		            md.update(salt.getBytes());
		            //Get the hash's bytes
		            byte[] bytes = md.digest(passwordToHash.getBytes());
		            //This bytes[] has bytes in decimal format;
		            //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            //Get complete hashed password in hex format
		            generatedPassword = sb.toString();
		        }
		        catch (NoSuchAlgorithmException e) {
		            e.printStackTrace();
		        }
		        return generatedPassword;
		    }
		     
		    //Add salt
		    private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
		    {
		        //Always use a SecureRandom generator
		        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		        //Create array for salt
		        byte[] salt = new byte[16];
		        //Get a random salt
		        sr.nextBytes(salt);
		        //return salt
		        return salt.toString();
		    }
		}
//public static String getMd5Digest(String input) {
//	try {
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		byte[] messageDigest = md.digest(input.getBytes());
//		BigInteger number = new BigInteger(1, messageDigest);
//		return number.toString(16);
//	} catch (NoSuchAlgorithmException e) {
//		return null;
//	}
//}