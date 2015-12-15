package science_project;

import edu.princeton.cs.introcs.In;

public class ShellSort {

	public ShellSort() {

	}

	public void sort(int[] a) {

		int increment = a.length / 2;
		while (increment > 0) {
			for (int i = increment; i < a.length; i++) {
				int j = i;
				int temp = a[i];
				while (j >= increment && a[j - increment] > temp) {
					a[j] = a[j - increment];
					j = j - increment;
				}
				a[j] = temp;
			}
			if (increment == 2) {
				increment = 1;
			} else {
				increment *= (5.0 / 11);
			}
		}
	}

	public static void main(String[] args) {
		int c;

		String filenameStr = "build/classes/science_project/test.data";
		In input = new In(filenameStr);
		int[] array = input.readAllInts();

		int n = array.length;

		System.out.print("In : ");
		for (int ii = 0; ii < array.length; ii++) {
			System.out.print(array[ii] + " ");
		}

		System.out.println(" ");

		ShellSort sort = new ShellSort();

		long startTime = System.nanoTime();
		sort.sort(array);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		// double second = (double) (duration/1000000000);

		// for (c = 0; c < (n - 1); c++) {
		// for (d = 0; d < n - c - 1; d++) {
		// if (array[d] > array[d + 1]) /* For descending order use < */
		// {
		// swap = array[d];
		// array[d] = array[d + 1];
		// array[d + 1] = swap;
		// }
		// }
		// }

		/*
		 * for i = 1:n, swapped = false for j = n:i+1, if a[j] < a[j-1], swap
		 * a[j,j-1] swapped = true → invariant: a[1..i] in final position break
		 * if not swapped end
		 */

		System.out.print("Out: ");

		for (c = 0; c < n; c++)
			System.out.print(array[c] + " ");

		System.out.println(" ");

		System.out.println("Time taken to sort is aproxamately " + duration
				+ " nanoseconds");

		System.out.println("The file was " + filenameStr);
		// System.out.println("Or about " + second + " seconds");

	}

}
