package sciencefair.myencrypt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MomEncrypt {
	
	private static Set<Integer> primeSet = 
			new HashSet<Integer>(Arrays.asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97));
	private static String padString = "!@#$%^&*()!!!";

	public static String hashAlgrithm01(String input) {
		
		String paddedPassword = padPassword(input);
		Object[] arr = primeSet.toArray();
		int charVal = input.charAt(input.length()-1);
		int primeNum = (Integer)arr[calPrimePosition(charVal)];
		String encryptPass = calNewPassword(paddedPassword, primeNum);

		int y = 0;
		
		for (int ii = 0; ii < paddedPassword.length(); ii++) {
			char charX = paddedPassword.charAt(ii);
			int ix = (int) charX;
			
			if (ix <= 61) {
				y = ix + 61;
			} else if (ix > 61) {
				y = ix + 61 - 122 + 47;
			}
			
            char charY = (char) y;
            //System.out.println(ix + " " + (char)ix +"=========" + y + " " + (char)y);
            encryptPass = encryptPass + String.valueOf(charY);
		}
		
		return encryptPass;
	}

	private static String calNewPassword(String paddedPassword, int primeNum) {
		StringBuilder strBuilder = new StringBuilder(primeNum);
		strBuilder.append(paddedPassword);
		if(paddedPassword.length() % 2 == 0)
		{
			strBuilder.append(primeNum);
		}
		return strBuilder.toString();
	}

	public static void main(String[] args) {
		String originalPassword = "abc";
		String hashStr = hashAlgrithm01(originalPassword);
		System.out.println("!!!!! hashStr = " + hashStr);
		
		String originalPassword1 = "abc";
		String hashStr1 = hashAlgrithm01(originalPassword1);
		System.out.println("!!!!!hashStr1 = " + hashStr1);
		
		String originalPassword2 = "abc.";
		String hashStr2 = hashAlgrithm01(originalPassword2);
		System.out.println("!!!!!hashStr2 = " + hashStr2);
		
		String originalPassword3 = "xyz";
		String hashStr3 = hashAlgrithm01(originalPassword3);
		System.out.println("!!!!!hashStr3 = " + hashStr3);
		
		String originalPassword4 = "aaa";
		String aa = hashAlgrithm01(originalPassword4);
		System.out.println("!!!!!hashStraaa = " + aa);
		
		String originalPassword5 = "aaa";
		String hashStr4 = hashAlgrithm01(originalPassword5);
		System.out.println("!!!!!hashStr5aaa = " + hashStr4);
		
		String originalPassword6 = "aaa1";
		String hashStr5 = hashAlgrithm01(originalPassword6);
		System.out.println("!!!!!hashStr2aaa6 = " + hashStr5);
		
		if(hashStr4.equals(hashStr5))
			System.out.println("!!!!!!!!SSSSSS= ");
		else
			System.out.println("!!!!!!!!FFFFFF ");
	}
	
	private static int calPrimePosition(int charVal)
	{
		int rVal = 0;
		int halfSize = primeSet.size()/2;
		if(charVal > primeSet.size())
		{
			rVal = halfSize + charVal % halfSize;
		}
		else
		{
			rVal = charVal % halfSize;
		}
		
		System.out.println("!!!!!rVal = " + rVal);
		
		return rVal;
	}
	
	private static String padPassword(String password)
	{
		StringBuilder strBuilder = new StringBuilder();
		int halfPasswordSize = password.length()/2; 
		strBuilder.append(password.substring(0, halfPasswordSize));
		strBuilder.append(padString);
		strBuilder.append(password.substring(halfPasswordSize, password.length() ));
		return strBuilder.toString();
	}

}
