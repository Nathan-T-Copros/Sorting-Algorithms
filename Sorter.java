public class Sorter {

	public static void main(String[] args) {
		final int RANGE = 1000000; //the range of random generated number
		
		long start; //to hold the initial time when timing the sorting
		int size = 10; 
		int[] ordered = new int[size]; //array that will become a sorted version of n20
		int[] n10 = new int[size]; //array of length size containing random generated number
		for(int i=0; i<size; i++)
			n10[i] = (int)(Math.random() * RANGE);
		
		/* Testing and showing the sorted the numbers for each sorting algorithm
		 * No timing on this section (except for bogo cause it takes too long with larger arrays
		 */
		
		start = System.currentTimeMillis(); //initial time 
		ordered = BogoSort(n10); //sorted array from bogosort
		System.out.println("Bogo took: " + (System.currentTimeMillis() - start) + " ms"); //time it took
		System.out.print("Bogo numbers"); //prints out the numbers in the sorted array as verification
		for(int i=0; i<size; i++) 
			System.out.print(", " +ordered[i]);	
		System.out.println("");
		
		ordered = SelectionSort(n10);
		System.out.print("Selection numbers");
		for(int i=0; i<size; i++)
			System.out.print(", " +ordered[i]);	
		System.out.println("");
		
		ordered = BubbleSort(n10);
		System.out.print("Bubble numbers");
		for(int i=0; i<size; i++)
			System.out.print(", " +ordered[i]);
		System.out.println("");
		
		ordered = MergeSort(n10);
		System.out.print("Merge numbers");
		for(int i=0; i<size; i++)
			System.out.print(", " +ordered[i]);	
		System.out.println("");
		
		/* Testing time difference between Selection, Bubble and Merge.
		 * Done with an array of 10000, 100000 and one of 1000000 numbers
		 */
		size = 10000; 
		ordered = new int[size]; 
		int[] n10k = new int[size]; 
		for(int i=0; i<size; i++)
			n10k[i] = (int)(Math.random() * RANGE);
		
		System.out.println("\n\n*Time for 10,000 numbers\n");
		start = System.currentTimeMillis(); 
		ordered = SelectionSort(n10k);
		System.out.println("Selection took: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis(); 
		ordered = BubbleSort(n10k);
		System.out.println("Bubble took: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis(); 
		ordered = MergeSort(n10k);
		System.out.println("Merge took: " + (System.currentTimeMillis() - start) + " ms");
		
		//100k array
		size = 100000; 
		ordered = new int[size]; 
		int[] n100k = new int[size]; 
		for(int i=0; i<size; i++)
			n100k[i] = (int)(Math.random() * RANGE);
		
		System.out.println("\n\n*Time for 100,000 numbers\n");
		start = System.currentTimeMillis(); 
		ordered = SelectionSort(n100k);
		System.out.println("Selection took: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis(); 
		ordered = BubbleSort(n100k);
		System.out.println("Bubble took: " + (System.currentTimeMillis() - start) + " ms");
		
		start = System.currentTimeMillis(); 
		ordered = MergeSort(n100k);
		System.out.println("Merge took: " + (System.currentTimeMillis() - start) + " ms");
		
		//try 1 million for merge sort
		size = 1000000; 
		ordered = new int[size]; 
		int[] n1M = new int[size]; 
		for(int i=0; i<size; i++)
			n1M[i] = (int)(Math.random() * RANGE);
		
		System.out.println("\n\n*Time for 1,000,000 numbers\n");
		start = System.currentTimeMillis(); 
		ordered = MergeSort(n1M);
		System.out.println("Merge took: " + (System.currentTimeMillis() - start) + " ms");
		
	}
		
	//Selection sort finds the smallest non-sorted number and place it
	//at the bottom of the non-sorted part of the array
	public static int[] SelectionSort(int[] array) { 
		int[] sorted = new int[array.length];
		for(int i=0; i < array.length; i++)
			sorted[i] = array[i];
		
		int temp, index;		
		
		for(int i=0; i < sorted.length - 1; i++) {
			index = i;
			for(int j=i+1; j < sorted.length; j++) {
				if(sorted[j] < sorted[index])
					index = j;				
			}
			temp = sorted[i];
			sorted[i] = sorted[index];
			sorted[index] = temp;
		}		
		return sorted;
	}
	
	//Switches each number with the one above if it's smaller. Repeat as many times as numbers there are
	public static int[] BubbleSort(int[] array) {
		int[] sorted = new int[array.length];
		int temp;
		
		for(int i=0; i<array.length; i++)
			sorted[i] = array[i];
		
		for(int i=0; i<array.length; i++)
			for(int j=0; j<array.length - 1; j++) {
				if(sorted[j] > sorted[j+1]) {
					temp = sorted[j];
					sorted[j] = sorted[j+1];
					sorted[j+1]	= temp;
				}
			}				
		
		return sorted;
	}
	
	//Associate each number to a random index in the array. If not sorted, repeat. Useless but funny.
	public static int[] BogoSort(int[] array) {
		int[] sorted = new int[array.length];
		int temp, index;
		
		for(int i=0; i<array.length; i++)
			sorted[i] = array[i];
		
		while(!isSorted(sorted)) {
			for(int i=0 ; i < sorted.length; i++) {
				index = (int) (Math.random() * sorted.length);
				temp = sorted[i];
				sorted[i] = sorted[index];
				sorted[index] = temp;
			}			
		}
		
		return sorted;
	}
	
	public static boolean isSorted(int[] array) {
		for(int i=0; i < array.length - 1; i++)
			if(array[i] > array[i + 1])
				return false;
		return true;
	}
	
	//using recursion, separate array into an number of arrays equal to its length
	//Then merges each of them, into sorted arrays. Repeat until initial array is sorted
	public static int[] MergeSort(int[] array) {
		if(array.length == 1)
			return array;
		
		int[] a = new int[(array.length)/2];
		int[] b = new int[array.length - a.length];
		for(int i=0; i < a.length; i++)
			a[i] = array[i];
		for(int j=a.length; j<array.length; j++)
			b[j - a.length] = array[j];
						
		return merge(MergeSort(a), MergeSort(b));
	}
	
	//function to merge two arrays
	public static int[] merge (int[] a, int[] b) {
		int[] array = new int[a.length + b.length];
		int index = 0, indexA = 0, indexB = 0;		
		
			while(indexA < a.length && indexB < b.length) {
				if(a[indexA] < b[indexB])
					array[index++] = a[indexA++];
				else
					array[index++] = b[indexB++];				
			}
						
			while(indexA < a.length)
				array[index++] = a[indexA++];
			while(indexB < b.length)
				array[index++] = b[indexB++];
		
		return array;
	}	
}