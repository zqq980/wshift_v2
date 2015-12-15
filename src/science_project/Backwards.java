package science_project;

import java.util.Random;

public class Backwards {



	public static void main(String[] args) {

		int nn = 500;
		int[] array = new int[nn];

		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}

		int[] arrayReverse = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayReverse[i] = array.length - i;
		}



		for (int ii = 0; ii < array.length; ii++) {
			System.out.println(array[ii]);
		}

	}

}
