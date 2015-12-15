package science_project;

import edu.princeton.cs.introcs.In;
import java.util.ArrayList;

public class SortAlgorithim {
	
	public static int[] selectionSort(int[] data){
		  int lenD = data.length;
		  int j = 0;
		  int tmp = 0;
		  for(int i=0;i<lenD;i++){
		    j = i;
		    for(int k = i;k<lenD;k++){
		      if(data[j]>data[k]){
		        j = k;
		      }
		    }
		    tmp = data[i];
		    data[i] = data[j];
		    data[j] = tmp;
		  }
		  return data;
		}
	
	public static int[] insertionSort(int[] data){
		  int len = data.length;
		  int key = 0;
		  int i = 0;
		  for(int j = 1;j<len;j++){
		    key = data[j];
		    i = j-1;
		    while(i>=0 && data[i]>key){
		      data[i+1] = data[i];
		      i = i-1;
		      data[i+1]=key;
		    }
		  }
		  return data;
		}
	
	public static int[] bubbleSort(int[] data){
		  int lenD = data.length;
		  int tmp = 0;
		  for(int i = 0;i<lenD;i++){
		    for(int j = (lenD-1);j>=(i+1);j--){
		      if(data[j]<data[j-1]){
		        tmp = data[j];
		        data[j]=data[j-1];
		        data[j-1]=tmp;
		      }
		    }
		  }
		  return data;
		}
	
	public static int[] quickSort(int[] data){
		int lenD = data.length;
		int pivot = 0;
		int ind = lenD/2;
		int i,j = 0,k = 0;
		if(lenD<2){
		  return data;
		}
		else{
		  int[] L = new int[lenD];
		  int[] R = new int[lenD];
		  int[] sorted = new int[lenD];
		  pivot = data[ind];
		  for(i=0;i<lenD;i++){
		    if(i!=ind){
		      if(data[i]<pivot){
		        L[j] = data[i];
		        j++;
		      }
		      else{
		        R[k] = data[i];
		        k++;
		      }
		    }
		  }
		  int[] sortedL = new int[j];
		  int[] sortedR = new int[k];
		  System.arraycopy(L, 0, sortedL, 0, j);
		  System.arraycopy(R, 0, sortedR, 0, k);
		  sortedL = quickSort(sortedL);
		  sortedR = quickSort(sortedR);
		  System.arraycopy(sortedL, 0, sorted, 0, j);
		  sorted[j] = pivot;
		  System.arraycopy(sortedR, 0, sorted, j+1, k);
		  return sorted;
		  }
		}
	
	public static int[] mergeSort(int[] data){
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
		    L = mergeSort(L);
		    R = mergeSort(R);
		    sorted = merge(L, R);
		    return sorted;
		  }
		}
		 
		public static int[] merge(int[] L, int[] R){
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
		
		public static int[] shellSort(int[] data){
			  int lenD = data.length;
			  int inc = lenD/2;
			  while(inc>0){
			    for(int i=inc;i<lenD;i++){
			      int tmp = data[i];
			      int j = i;
			      while(j>=inc && data[j-inc]>tmp){
			        data[j] = data[j-inc];
			        j = j-inc;
			      }
			      data[j] = tmp;
			    }
			    inc = (inc /2);
			  }
			  return data;
			}
		
		public static int[] heapSort(int[] a){
			int count = a.length;
		 
			heapify(a, count);
		 
			int end = count - 1;
			while(end > 0){

				int tmp = a[end];
				a[end] = a[0];
				a[0] = tmp;

				siftDown(a, 0, end - 1);

				end--;
			}
			return a;
		}
		 
		public static void heapify(int[] a, int count){

			int start = (count - 2) / 2; 
		 
			while(start >= 0){

				siftDown(a, start, count - 1);
				start--;
			}

		}
		 
		public static void siftDown(int[] a, int start, int end){
			int root = start;
		 
			while((root * 2 + 1) <= end){  
				int child = root * 2 + 1; 
				if(child + 1 <= end && a[child] < a[child + 1])
					child = child + 1;       
				if(a[root] < a[child]){   
					int tmp = a[root];
					a[root] = a[child];
					a[child] = tmp;
					root = child;
				}else
					return;
			}
		}
		
//		public static void radixSort(int arr[], int maxDigits){
//			int exp = 1;//10^0;
//			for(int i =0; i < maxDigits; i++){
//				ArrayList bucketList[] = new ArrayList[10];
//				for(int k=0; k < 10; k++){
//					bucketList[k] = new ArrayList();
//				}
//				for(int j =0; j < arr.length; j++){
//					int number = (arr[j]/exp)%10;
//					bucketList[number].add(arr[j]);
//				}
//				exp *= 10;
//				int index =0;
//				for(int k=0; k < 10; k++){
//					for(int num: bucketList[k]){
//						arr[index] = num;
//						index++;
//					}
//				}
//			}
//
//			System.out.println("Sorted numbers");
//			for(int i =0; i < arr.length; i++){
//				System.out.print(arr[i] +", ");
//			}
//		}
//
//		public static void main(String[] argv){
//			int n = 5;
//			int arr[] = {1,4,2,3,5,10,8};
//			new RadixSort().radixSort(arr, 2);
//		}
		
		public static int[] radixsort(int[] old) {
		    // Loop for every bit in the integers
		    for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
		        // The array to put the partially sorted array into
		        int[] tmp = new int[old.length];
		        // The number of 0s
		        int j = 0;
		 
		        // Move the 0s to the new array, and the 1s to the old one
		        for (int i = 0; i < old.length; i++) {
		            // If there is a 1 in the bit we are testing, the number will be negative
		            boolean move = old[i] << shift >= 0;
		 
		            // If this is the last bit, negative numbers are actually lower
		            if (shift == 0 ? !move : move) {
		                tmp[j] = old[i];
		                j++;
		            } else {
		                // It's a 1, so stick it in the old array for now
		                old[i - j] = old[i];
		            }
		        }
		 
		        // Copy over the 1s from the old array
		        for (int i = j; i < tmp.length; i++) {
		            tmp[i] = old[i - j];
		        }
		 
		        // And now the tmp array gets switched for another round of sorting
		        old = tmp;
		    }
		 
		    return old;
		}
		
		public static int[] combSort(int [] input) {
			//<E extends Comparable<? super E>> void
		    int gap = input.length;
		    boolean swapped = true;
		    while (gap > 1 || swapped) {
		        if (gap > 1) {
		            gap = (int) (gap / 1.3);
		        }
		        swapped = false;
		        for (int i = 0; i + gap < input.length; i++) {
		        	// if (input[i].compareTo(input[i + gap]) > 0) {
		            if (input[i] > input[i + gap]) {
		                int t = input[i];
		                input[i] = input[i + gap];
		                input[i + gap] = t;
		                swapped = true;
		            }
		        }
		    }
			return input;
		}
		
	public static void main(String[] args) {
		
		int algortithm = 9;
		// 1 is Selection Sort
		// 2 is Insertion Sort
		// 3 is Bubble Sort
		// 4 is Quick Sort
		// 5 is Merge Sort
		// 6 is Shell Sort
		// 7 is Heap Sort
		// 8 is Radix Sort
		// 9 is Comb Sort
		
		int c;
		long startTime = 0;
		long endTime = 0;

		String filenameStr = "build/classes/science_project/randomSort";
		In input = new In(filenameStr);
		int[] array = input.readAllInts();

		int n = array.length;
		int[] sortedArray = new int[n];
		
		
		System.out.print("In : ");
		for (int ii = 0; ii < array.length; ii++) {
			System.out.print(array[ii] + " ");
		}


		
		if (algortithm == 1){
			startTime = System.nanoTime();
			sortedArray = selectionSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 2){
			startTime = System.nanoTime();
			sortedArray = insertionSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 3){
			startTime = System.nanoTime();
			sortedArray = bubbleSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 4){
			startTime = System.nanoTime();
			sortedArray = quickSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 5){
			startTime = System.nanoTime();
			sortedArray = mergeSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 6){
			startTime = System.nanoTime();
			sortedArray = shellSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 7){
			startTime = System.nanoTime();
			sortedArray = heapSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 8){
			startTime = System.nanoTime();
			sortedArray = radixsort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 9){
			startTime = System.nanoTime();
			sortedArray = combSort(array);
			endTime = System.nanoTime();
		}



		long duration = (endTime - startTime);

		System.out.println(" ");
		
		System.out.print("Out: ");

		for (c = 0; c < n; c++)
			System.out.print(sortedArray[c] + " ");

		System.out.println(" "); 

		System.out.println("Time taken to sort is aproxamately " + duration
				+ " nanoseconds");

		if (algortithm == 1){
			System.out.println ("The sort used was Selection sort");
		}
		
		if (algortithm == 2){
			System.out.println ("The sort used was Insertion sort");
		}
		
		if (algortithm == 3){
			System.out.println ("The sort used was Bubble sort");
		}
		
		if (algortithm == 4){
			System.out.println ("The sort used was Quick sort");
		}
		
		if (algortithm == 5){
			System.out.println ("The sort used was Merge sort");
		}
		
		if (algortithm == 6){
			System.out.println ("The sort used was Shell sort");
		}
		
		if (algortithm == 7){
			System.out.println ("The sort used was Heap sort");
		}
		
		if (algortithm == 8){
			System.out.println ("The sort used was Radix sort");
		}
		
		if (algortithm == 9){
			System.out.println ("The sort used was Comb sort");
		}
		
		// 1 is Selection Sort
		// 2 is Insertion Sort
		// 3 is Bubble Sort
		// 4 is Quick Sort
		// 5 is Merge Sort
		// 6 is Shell Sort
		// 7 is Heap Sort
		// 8 is Radix Sort
		// 9 is Comb Sort
		
		System.out.println("The file was " + filenameStr);
		// System.out.println("Or about " + second + " seconds");

	}
}
