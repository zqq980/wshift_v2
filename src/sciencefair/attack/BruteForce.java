package sciencefair.attack;

/*
 Variables involved:

 P: an instance of our problem.
 c: a candidate for solving the problem instance P.

 Four methods are required:

 first (P): generates the first candidate for P.
 next (P, c): generates the next candidate.
 valid (P, c): checks whether a candidate c is a solution for problem instance P.
 output (P, c): do something with the solution once we've found it.

 So finally our algorithm looks like this:

 c = first(P)
 while (c is in the search space)
 if valid(P,c) then output(P, c)
 else c = next(P,c)

 A simple example is trying to solve for a given number… let’s say P = 5. 
 Our first candidate would be 1, and we would increment this until we reached 5. 
 So this is really straight forward and pretty inefficient. However. 
 there are some optimizations which we’ll discuss later on.
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import sciencefair.myencrypt.MyEncrypt_v01;

import com.howtodoinjava.hashing.password.demo.advanced.ReallyStrongSecuredPassword;
import com.howtodoinjava.hashing.password.demo.bcrypt.BCrypt;
import com.howtodoinjava.hashing.password.demo.md5.SaltedMD5Example;
import com.howtodoinjava.hashing.password.demo.sha.SHAExample;
import com.lambdaworks.crypto.SCryptUtil;

public class BruteForce {

	private static final String FIRST_CANDIDATE = "0";
	private static final char START_OF_SEARCH_AREA = '0';
	private static final char END_OF_SEARCH_AREA = 'z';
	
	private static String salt;

	public static String getMd5Digest(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String first() {
		return FIRST_CANDIDATE;
	}

	public static String next(String c) {
		String next = "";
		int N = c.length();
		char[] a = c.toCharArray();
		for (int j = N - 1; j >= 0; j--) {
			a[j] = nextChar(a[j]);
			if (!(a[j] == START_OF_SEARCH_AREA)) {
				next = new String(a);
				break;
			} else {
				if (j == 0) {
					next = new String(a) + START_OF_SEARCH_AREA;
				}
			}
		}
		return next;
	}

	private static char nextChar(char ch) {
		if (ch == END_OF_SEARCH_AREA) {
			return START_OF_SEARCH_AREA;
		} else {
			return ++ch;
		}
	}

	public static boolean valid(String P, String c, int encrypt) {
		
		boolean validBL = false;
		
		if (encrypt == 0){
			validBL = c.equals(P); 
		} else if (encrypt == 1){
			validBL = getMd5Digest(c).equals(P);
		} else if (encrypt == 2){

			try {
//				System.out.println(P);
//			    System.out.println(ReallyStrongSecuredPassword.generateStorngPasswordHash(c));
			    
				// validBL = PBKDF2.generateStorngPasswordHash(c).equals(P);
				validBL = ReallyStrongSecuredPassword.validatePassword(c , P);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (encrypt == 3){

					
//					String d = SHAExample.get_SHA_1_SecurePassword(c, salt);
//					System.out.println(P);
//				    System.out.println(d);
					
					validBL = SHAExample.get_SHA_1_SecurePassword(c, salt).equals(P);


		} else if (encrypt == 4){
			validBL = BCrypt.checkpw(c, P);
		} else if (encrypt == 5){
			validBL = SCryptUtil.check(c, P);
		} else if (encrypt == 6){
//			String d = SaltedMD5Example.getSecurePassword(c, salt);
//			System.out.println(P);
//		    System.out.println(d);
	
		} else if (encrypt == 7){
			validBL = MyEncrypt_v01.encrypt01(c).equals(P); 
			

		}
		

		return validBL;
		
	}

	public static String output(String P, String c) {
		System.out.println("the solution for " + P + " is : " + c);
		return null;
	}

	public static void solveForP(String P, int encrypt) {
		int ii = 1;
		String c = first();
		//System.out.println(ii + " the current charecter " + c);
		if (valid(P, c, encrypt)) {
			output(P, c);

		} else {
			while ((c = next(c)) != null) {
			//	ii++;
			//	System.out.println(ii + " the current charecter " + c);
				if (valid(P, c, encrypt)) {
					output(P, c);
					break;
				}
				
				// if (ii > 60) break;
			}
		}
	}

	public static void main(String[] args) {
		int encrypt = 7;
		
		// passwords: wxyz - 
		String hashedText = "aaa1";
		
		// encrypt = 1 - MD5
		
		if (encrypt == 1){
			hashedText = getMd5Digest(hashedText);	
		} 
		
		// encrypt = 2 - PBKDF2
		
		if (encrypt == 2){

			try {
				hashedText = ReallyStrongSecuredPassword.generateStorngPasswordHash(hashedText);
			    System.out.println(ReallyStrongSecuredPassword.generateStorngPasswordHash("a"));
			    System.out.println(ReallyStrongSecuredPassword.generateStorngPasswordHash("a"));
			    
//				hashedText = PBKDF2.generateStorngPasswordHash(hashedText);
//			    System.out.println(PBKDF2.generateStorngPasswordHash("a"));
//			    System.out.println(PBKDF2.generateStorngPasswordHash("a"));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// encrypt = 3 - SHA salted
		
		if (encrypt == 3){

			try {
				salt = SHAExample.getSalt();
				
//			    System.out.println(SHAExample.get_SHA_1_SecurePassword("a", salt));
//			    System.out.println(SHAExample.get_SHA_1_SecurePassword("a", salt));
			    // System.exit(0);
			    
				hashedText = SHAExample.get_SHA_1_SecurePassword(hashedText, salt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// encrypt = 4 - BCrypt
		
		if (encrypt == 4){
			hashedText = BCrypt.hashpw(hashedText, BCrypt.gensalt(12));
		}
		
		// encrypt = 5 - SCrypt
		
		if (encrypt == 5){
			hashedText = SCryptUtil.scrypt(hashedText, 16, 16, 16);
		}
		
		// encrypt = 6 - MD5 Salted
		
		if (encrypt == 6){
			try {
				salt = SaltedMD5Example.getSalt();
				hashedText =  SaltedMD5Example.getSecurePassword(hashedText, salt);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// encrypt = 7 - MyEncrypt WShift Version 01
		
		if (encrypt == 7){
			hashedText = MyEncrypt_v01.encrypt01(hashedText);
		}

		// System.exit(0);
		
		// String hashedText = getMd5Digest("largo");
		//String hashedText = getMd5Digest("small");
		
		
		System.out.println("Hash Value : " + hashedText);
		long startTime = System.currentTimeMillis();
		solveForP(hashedText, encrypt);
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println("time to solve : " + (timeElapsed) + " millisecond(s)");
		System.out.println("time to solve : " + (timeElapsed/1000) + " second(s)");
	}

}