package science_project;

import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.Random;

public class ScienceFairFaster {

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
		

		
		public static int[] radixsort(int[] old) {
		    for (int shift = Integer.SIZE - 1; shift > -1; shift--) {
		        int[] tmp = new int[old.length];
		        int j = 0;
		 
		        for (int i = 0; i < old.length; i++) {
		            boolean move = old[i] << shift >= 0;
		 
		            if (shift == 0 ? !move : move) {
		                tmp[j] = old[i];
		                j++;
		            } else {
		                old[i - j] = old[i];
		            }
		        }
		 
		        for (int i = j; i < tmp.length; i++) {
		            tmp[i] = old[i - j];
		        }
		 
		        old = tmp;
		    }
		 
		    return old;
		}
		
		public static int[] combSort(int [] input) {
		    int gap = input.length;
		    boolean swapped = true;
		    while (gap > 1 || swapped) {
		        if (gap > 1) {
		            gap = (int) (gap / 1.3);
		        }
		        swapped = false;
		        for (int i = 0; i + gap < input.length; i++) {
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
		
		int nas = 6;
		int nag = 9;
		
		String dataAssortment;
		int assortment = 3;
		// 1 is for an imported file
		// 2 is for a sorted arrangement of data
		// 3 is for a random arrangement of data
		// 4 is for a reversed arrangement of data
		// 5 is for a almost sorted arrangement of data
		// 6 is for an arrangement of data with simmilar values.

		
		int algortithm = 1;
		// 1 is Selection Sort
		// 2 is Insertion Sort
		// 3 is Bubble Sort
		// 4 is Quick Sort
		// 5 is Merge Sort
		// 6 is Shell Sort
		// 7 is Heap Sort
		// 8 is Radix Sort
		// 9 is Comb Sort
		
		long[][] cputime = new long[nas+1][nag+1];
		

		int c;
		long startTime = 0;
		long endTime = 0;
		
		String filenameStr = new String();
		int[] array_new = null;
		
		for (int is = 3; is <= nas ; is++) {
			assortment = is;
			//for (int ia = 1; ia <= nag ; ia++) {
			
		if (assortment == 1){
			filenameStr = "build/classes/science_project/randomSort";
			In input = new In(filenameStr);
			array_new = input.readAllInts();
			//dataAssortment = filenameStr;
		} 
			
		else if (assortment == 2){
			array_new = DataArrays.sortedData();
			dataAssortment = "sortedData";
		}
		
		else if (assortment == 3){
			array_new = DataArrays.randomData();
			dataAssortment = "randomData";
		}
		
		else if (assortment == 4){
			array_new = DataArrays.reverseData();
			dataAssortment = "reverseData";
		}
		
		else if (assortment == 5){
			array_new = DataArrays.almostSortedData2();
			dataAssortment = "almostSortedData2";
		}

		else if (assortment == 6){
			array_new = DataArrays.simmilarValuesData();
			dataAssortment = "simmilarValuesData";
		}


		int n = array_new.length;
		int[] sortedArray = new int[n];
		
		 for (int ia = 1; ia <= nag ; ia++) {
			 
		 int[] array = new int[array_new.length];
		 
		 for (int iii = 0; iii < array_new.length ; iii++) {
				 array[iii] = array_new[iii];
		 }

			 
		algortithm = ia;
		
		if (algortithm == 1){
			startTime = System.nanoTime();
			sortedArray = selectionSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 2){
			startTime = System.nanoTime();
			sortedArray = insertionSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 3){
			startTime = System.nanoTime();
			sortedArray = bubbleSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 4){
			startTime = System.nanoTime();
			sortedArray = quickSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 5){
			startTime = System.nanoTime();
			sortedArray = mergeSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 6){
			startTime = System.nanoTime();
			sortedArray = shellSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 7){
			startTime = System.nanoTime();
			sortedArray = heapSort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 8){
			startTime = System.nanoTime();
			sortedArray = radixsort(array);
			endTime = System.nanoTime();
		}
		
		else if (algortithm == 9){
			startTime = System.nanoTime();
			sortedArray = combSort(array);
			endTime = System.nanoTime();
		}



		long duration = (endTime - startTime);
		cputime[is][ia] = duration;
		
		
		//System.out.println("The data assortment was " + filenameStr);
		// System.out.println("Or about " + second + " seconds");
		System.out.println ();
			}
		}
		
        System.out.println();
        
		for (int is = 3; is <= nas ; is++) {
			System.out.println("When IS = " + is );
			for (int ia = 1; ia <= nag ; ia++) {
				System.out.print(cputime[is][ia] + "  ");
			}
			System.out.println();
		}
	}
}
