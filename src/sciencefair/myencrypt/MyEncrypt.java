package sciencefair.myencrypt;

public class MyEncrypt {
	
	private static String padString = "!@#$%^&*()!!!";

	public static String encrypt01(String input) {
	
		String password = input + padString;
		String encryptPass = new String();

		int y = 0;

		int start = calRandomParam(input, 0);
		int acsiRange = calRandomRange(input, start);
		int end = calRandomParam(input, input.length()-1);
		
		for (int ii = 0; ii < password.length(); ii++) {
			char charX = password.charAt(ii);
			int ix = (int) charX;
			
			if (ix <= start) {
				y = 33+ (ix + start) % (127 - 33);
			} else if (ix > start) {
				y = 33+ (ix + start - acsiRange + end) % (127 - 33);
			}
			
            char charY = (char) y;
            //System.out.println(ix + " " + (char)ix +"=========" + y + " " + (char)y);
            encryptPass = encryptPass + String.valueOf(charY);
		}
		
		return encryptPass;
	}

	private static int calRandomRange(String input, int start) {
		// TODO Auto-generated method stub
		char[] arr = input.toCharArray();
		int rangBase = 0;
		
		for(int chartmp : arr)
		{
			rangBase += chartmp;
		}
		//System.out.println("!!!!!hashStr3 rangBase = " + rangBase);
		
		//System.out.println("!!!!!hashStr3 rang = " + rangBase % 122);
		return rangBase;
	}

	private static int calRandomParam(String input, int pos) {
		return input.length()/2 + input.charAt(pos);
	}

	public static void main(String[] args) {
		String originalPassword = "aaa";
		String aa = encrypt01(originalPassword);
		System.out.println("!!!!!hashStraaa = " + aa);
		
		String originalPassword1 = "aaa";
		String hashStr1 = encrypt01(originalPassword1);
		System.out.println("!!!!!hashStr1aaa = " + hashStr1);
		
		String originalPassword2 = "aaa1";
		String hashStr2 = encrypt01(originalPassword2);
		System.out.println("!!!!!hashStr2aaa1 = " + hashStr2);
		
		if(!hashStr1.equals(hashStr2))
			System.out.println("!!!!!!!!SSSSSS= ");
		else
			System.out.println("!!!!!!!!FFFFFF ");
		
		String originalPassword3 = "xyz";
		String hashStr3 = encrypt01(originalPassword3);
		System.out.println("!!!!!hashStr3xyz = " + hashStr3);
		
		String text = "I have a smart dag";
		String hashStr4 = encrypt01(text);
		System.out.println("!!!!!hashStr4 dogtext = " + hashStr4);
		
		String text1 = "I have a smart dag";
		String hashStr5 = encrypt01(text1);
		System.out.println("!!!!!!!!again5 dogtext = " + hashStr5);
		
		String text2 = "I have a smart dag.";
		String hashStr6 = encrypt01(text2);
		System.out.println("!!!!!!!!   with period = " + hashStr6);
		
		String text3 = "I have a smart dag.";
		String hashStr7 = encrypt01(text2);
		if(hashStr6.equals(hashStr7))
			System.out.println("!!!!!!!!SSSSSS= ");
		else
			System.out.println("!!!!!!!!FFFFFF ");
		System.out.println("!!!!!!!!again dogtext = " + hashStr6);
	}

}
