package science_project;


import edu.princeton.cs.introcs.In;

public class MySort {

	private int[] left;
	private int[] right;
	
	public MySort() {

	}
	
	public void splitArry(int[] data){
		int n = data.length;
		
		int aleft = (int)(n/2);
		left = new int[aleft];
		// System.out.print("left : ");
		for (int ii = 0; ii < left.length; ii++) {
			left[ii] = data[ii];
		//	System.out.print(left[ii] + " ");
		}
		// System.out.println();

		int aright = n - aleft;
		right = new int[aright];
		// System.out.print("right : ");
		for (int ii = 0; ii < right.length; ii++) {
			right[ii] = data[ii+ aleft];
		//	System.out.print(right[ii] + " ");
		}
		// System.out.println();
	
	}

	public int[] sort(int[] data){
		
		splitArry(data);
		
		System.out.print("left : ");
		for (int ii = 0; ii < left.length; ii++) {
			System.out.print(left[ii] + " ");
		}
		System.out.println();

		System.out.print("right : ");
		for (int ii = 0; ii < right.length; ii++) {
			System.out.print(right[ii] + " ");
		}
		System.out.println();

		int[] leftSorted = ScienceFair.shellSort(left);
		int[] rightSorted = ScienceFair.shellSort(right);
		
		int[] dataSorted = merge(left, right);
		
		System.out.print("dataSorted : ");
		for (int ii = 0; ii < dataSorted.length; ii++) {
			System.out.print(dataSorted[ii] + " ");
		}
		System.out.println();
		
		return dataSorted;
	}
	
	public int[] mergeSort(int[] data){
		  int lenD = data.length;
		  if(lenD<=1){
		    return data;
		  }
		  else{
		    int[] sorted = new int[lenD];
		    int middle = lenD/2;
		    int rem = lenD-middle;
		    int[] L = new int[middle];
		    int[] R = new int[rem];
		    System.arraycopy(data, 0, L, 0, middle);
		    System.arraycopy(data, middle, R, 0, rem);
		    L = this.mergeSort(L);
		    R = this.mergeSort(R);
		    sorted = merge(L, R);
		    return sorted;
		  }
		}
		 

		public int[] merge(int[] L, int[] R){
		  int lenL = L.length;
		  int lenR = R.length;
		  int[] merged = new int[lenL+lenR];
		  int i = 0;
		  int j = 0;
		  while(i<lenL||j<lenR){
		    if(i<lenL & j<lenR){
		      if(L[i]<=R[j]){
		        merged[i+j] = L[i];
		        i++;
		      }
		      else{
		        merged[i+j] = R[j];
		        j++;
		      }
		    }
		    else if(i<lenL){
		      merged[i+j] = L[i];
		      i++;
		    }
		    else if(j<lenR){
		      merged[i+j] = R[j];
		      j++;
		     }
		   }
		   return merged;
		}

	public static void main(String[] args) {
		int c;

		String filenameStr = "src/science_project/test.data";
		In input = new In(filenameStr);
		int[] array = input.readAllInts();

		int n = array.length;

		System.out.print("In : ");
		for (int ii = 0; ii < array.length; ii++) {
			System.out.print(array[ii] + " ");
		}

		System.out.println(" ");

		MySort mySort = new MySort();

		long startTime = System.nanoTime();
		//mySort.splitArry(array);
		mySort.sort(array);
		
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
