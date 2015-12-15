package science_project;

import edu.princeton.cs.introcs.In;

public class SelectionlSort {

	public SelectionlSort() {

	}

	public void sort(int[] nums){
		for(int currentPlace = 0;currentPlace<nums.length-1;currentPlace++){
			int smallest = Integer.MAX_VALUE;
			int smallestAt = currentPlace+1;
			for(int check = currentPlace; check<nums.length;check++){
				if(nums[check]<smallest){
					smallestAt = check;
					smallest = nums[check];
				}
			}
			int temp = nums[currentPlace];
			nums[currentPlace] = nums[smallestAt];
			nums[smallestAt] = temp;
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

		SelectionlSort sort = new SelectionlSort();

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
