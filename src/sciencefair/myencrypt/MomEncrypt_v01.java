package sciencefair.myencrypt;

public class MomEncrypt_v01 {

	public static String encrypt01(String input) {
		
		String password = input;
		String encryptPass = new String();

		int y = 0;
		
		for (int ii = 0; ii < password.length(); ii++) {
			char charX = password.charAt(ii);
			int ix = (int) charX;
			
			if (ix <= 61) {
				y = ix + 61;
			} else if (ix > 61) {
				y = ix + 61 - 122 + 47;
			}
			
            char charY = (char) y;
            System.out.println(ix + " " + (char)ix +"=========" + y + " " + (char)y);
            encryptPass = encryptPass + String.valueOf(charY);
		}
		
		return encryptPass;
	}

	public static void main(String[] args) {
		String originalPassword = "abc";
		String aa = MomEncrypt_v01.encrypt01(originalPassword);
		System.out.println(aa);
	}

}
