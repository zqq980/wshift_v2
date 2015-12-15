package science_project;

import java.util.Random;

public class DataArrays {

	static int nn = 100;
	
	static int[] randomData() {
		

		int[] array = new int[nn];

		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		
		Random rnd = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = array[index];
			array[index] = array[i];
			array[i] = a;
		}
		return array;
	}

	static int[] sortedData() {
		
		int[] array = new int[nn];

		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
		return array;
	}	

	static int[] almostSortedData() {
		int nn2 = (int)((nn*10) / 100);
	
		int[] array = new int[nn];

		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}

		Random rnd = new Random();
		for (int i = nn2 - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1 + 100);
			int index2 = rnd.nextInt(i + 2);
			int a = array[index];
			array[index] = array[index2];
			array[index2] = a;
		}
		
		return array;
	}	

	static int[] almostSortedData2() {
		int nn2 = (int)((nn*10) / 100);
	
		int[] array = new int[nn];

		Random rnd = new Random();
		for (int i = 0; i < array.length; i++) {
			int index = rnd.nextInt(nn);
			array[i] = index;
		}
		
		//Random rnd = new Random();
		for (int i = nn2 - 1; i > 0; i--) {
			int index = rnd.nextInt(nn2);
			//int index2 = rnd.nextInt(i + 2);
			array[index] = index;

		}
		
		return array;
	}	
	
	static int[] simmilarValuesData() {
		
		int nvalue = 5;
		
		int nend = 0;
		int nstr = 0;
		
		int nn2 = (int)((nn*5) / 100);
	
		int[] array = new int[nn];

		Random rnd = new Random();
		int index = rnd.nextInt(nn);
		
		nstr = 0;
		nend = array.length - (array.length / nvalue ) * (nvalue - 1);

		for (int i = nstr; i < nend; i++) {
			array[i] = index;
		}

		nstr = nend;
		nend = array.length - (array.length / nvalue ) * (nvalue - 2);
		index = rnd.nextInt(nn);
		for (int i = nstr; i < nend; i++) {
			array[i] = index;
		}

		nstr = nend;
		nend = array.length - (array.length / nvalue ) * (nvalue - 3);
		index = rnd.nextInt(nn);
		for (int i = nstr; i < nend; i++) {
			array[i] = index;
		}
		
		nstr = nend;
		nend = array.length - (array.length / nvalue ) * (nvalue - 4);
		index = rnd.nextInt(nn);
		for (int i = nstr; i < nend; i++) {
			array[i] = index;
		}
		
		nstr = nend;
		nend = array.length;
		index = rnd.nextInt(nn);
		for (int i = nstr; i < nend; i++) {
			array[i] = index;
		}
//		
//		index = rnd.nextInt(nn);
//		for (int i = 100; i < array.length - (array.length / nvalue ) * (nvalue - 2); i++) {
//			array[i] = index;
//		}
//		
//		index = rnd.nextInt(nn);
//		for (int i = 200; i < array.length - (array.length / nvalue ) * (nvalue - 3); i++) {
//			array[i] = index;
//		}
//		
//		index = rnd.nextInt(nn);
//		for (int i = 300; i < array.length - (array.length / nvalue ) * (nvalue - 4); i++) {
//			array[i] = index;
//		}
//		
//		index = rnd.nextInt(nn);
//		for (int i = 400; i < array.length; i++) {
//			array[i] = index;
//		}
		
		Random rnd2 = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index2 = rnd.nextInt(i + 1);
			int a = array[index2];
			array[index2] = array[i];
			array[i] = a;
		}
		//Random rnd = new Random();
//		for (int i = nn2 - 1; i > 0; i--) {
//			int index2 = rnd.nextInt(nn2);
//			//int index2 = rnd.nextInt(i + 2);
//			array[index2] = index2;
//
//		}
		
		return array;
	}	
	
	public static int[] reverseData() {

		int[] arrayReverse = new int[nn];
		for (int i = 0; i < arrayReverse.length; i++) {
			arrayReverse[i] = arrayReverse.length - i;
		}

		return arrayReverse;

	}
	
	public static void main(String[] args) {

		int[] array = new int[nn];

		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}

		int[] arrayReverse = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayReverse[i] = array.length - i;
		}

		
		//array = randomData();
		//array = almostSortedData();
		array = simmilarValuesData();
		

		for (int ii = 0; ii < array.length; ii++) {
			System.out.println(array[ii]);
		}

	}

}
