package sciencefair.myencrypt;

import org.junit.Test;

import sciencefair.myencrypt.MyEncrypt_v01;

public class TestHashAlgrithm {

	@Test
	public void test() {	
		String input = "aaa";
		String hashedText =MyEncrypt_v01.encrypt01(input);
		System.out.println("!!!!! hashedText = " + hashedText );
		
		input = "aaa1";
		hashedText = MyEncrypt_v01.encrypt01(input);
		System.out.println("!!!!! changed hashedText = " + hashedText ); 
	}

}
