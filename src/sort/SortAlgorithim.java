package sort;

import edu.princeton.cs.introcs.In;

public class SortAlgorithim {
	
	public static int[] SelectionSort(int[] data){
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
	
	public static int[] InsertionSort(int[] data){
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
		
		
	
	public static void main(String[] args) {
		int algortithm = 6;
		int c;


		String filenameStr = "bin/sort/test.data";
		In input = new In(filenameStr);
		int[] array = input.readAllInts();

		int n = array.length;
		int[] sortedArray = new int[n];
		
		System.out.println(" ");
		
		System.out.print("In : ");
		for (int ii = 0; ii < array.length; ii++) {
			System.out.print(array[ii] + " ");
		}


		long startTime = 0;
		long endTime = 0;

		if (algortithm == 1){
			startTime = System.nanoTime();
			sortedArray = SelectionSort(array);
			endTime = System.nanoTime();
		}
		
		if (algortithm == 2){
			
			startTime = System.nanoTime();
			sortedArray = InsertionSort(array);
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
			System.out.print(sortedArray[c] + " ");

		System.out.println(" ");

		System.out.println("Time taken to sort is aproxamately " + duration
				+ " nanoseconds");

		System.out.println("The file was " + filenameStr);
		// System.out.println("Or about " + second + " seconds");

	}
}
