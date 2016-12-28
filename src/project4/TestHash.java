package project4;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

/**
 * 
 * Title: Program 4 
 * Files: TestHash.java, HashTable.java, LinkedList.java, ListNode.java,
 * Questions.txt
 * 
 * Semester: Fall 2016 
 * 
 * Author: Katrina Van Laan 
 * Email:vanlaan@wisc.edu
 * Lecturer's Name: Charles Fischer
 *
 */

/**
 * Class header:
 * 
 * Instructors notes:
 * 
 * This program tests some of the functionality of the <tt>HashTable</tt> class.
 * It does not completely test the <tt>HashTable</tt> class. You should make
 * sure that you do completely test your <tt>HashTable</tt> class, either by
 * modifying this file or by writing a different driver.
 * 
 * @author Charles Fischer
 **/
public class TestHash {

	/**
	 * Main method to run the <tt>HashTable</tt> class.
	 *
	 * @param args
	 *            needs to have 6 values in the array:
	 * @param [0]
	 *            is the number of items that will be hashed into the table.
	 * @param [1]
	 *            the random number seed (integer)to use in generating values
	 * @param [2]
	 *            maximum load factor to give the the <tt>HashTable</tt> class.
	 *            Note it is a fractional amount (e.g., 0.45), not the percent.
	 * @param [3]
	 *            the initial size of the hashtable to give to
	 *            <tt>HashTable</tt> class.
	 * @param [4]
	 *            maximum chain length to give the <tt>HashTable</tt> class. To
	 *            indicate no maximum chain length, the value 0 will be given.
	 * @param [5]
	 *            output file name used when calling the <tt>dump()</tt> or
	 *            <tt>displayStats()</tt> methods.
	 */

	static int[] A = new int[] { 80, 90, 50, 10, 80, 70, 30, 40, 70, 50, 40, 20, 60 };
	int low = 0;
	int high = 12;

	public static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static int medianOfThree(int[] intArray, int left, int right) {

		int center = (left + right) / 2;
		System.out.println(A[left]);

		System.out.println(A[right]);

		System.out.println(A[center]);
		if (intArray[left] > intArray[center])
			swap(intArray, left, center);

		System.out.println("");
		System.out.println("Swapping low and lowest of median of three");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
		}

		System.out.println("");
		if (intArray[left] > intArray[right]) {
			swap(intArray, left, right);

			System.out.println("");
			System.out.println("Swapping low and high if low is higher");
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i]);
			}
		}

		System.out.println("");
		if (intArray[center] > intArray[right]) {
			swap(intArray, center, right);

			System.out.println("");
			System.out.println("Swapping center and high if center is higher");
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i]);
			}
		}

		System.out.println("");
		swap(intArray, center, right - 1);

		System.out.println("");
		System.out.println("Swap the pivot with the value in A[high-1]");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
		}

		System.out.println("");
		return intArray[right - 1];
	}

	public static <E extends Comparable<E>> void quickSort(int[] A) {

		quickAux(A, 0, A.length - 1);
	}

	public static void quickSortTwo(int[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}

			while (arr[j] > pivot) {
				j--;
			}

			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;

				System.out.println("");
				System.out.println(" QuickSort Two swap");
				for (int z = 0; z < A.length; z++) {
					System.out.print(A[z]);
				}

				System.out.println("");
			}
		}
		System.out.println("REcursion begins");
		// recursively sort two sub parts
		if (low < j)
			quickSortTwo(arr, low, j);

		if (high > i)
			quickSortTwo(arr, i, high);

	}

	private static <E extends Comparable<E>> void quickAux(int[] intArray, int left, int right) {

		int size = right - left + 1;
		if (size <= 3)
			manualSort(intArray, left, right);
		else {
			double median = medianOfThree(intArray, left, right);
			int partition = partition(intArray, left, right, median);
			quickAux(intArray, left, partition - 1);
			quickAux(intArray, partition + 1, right);
		}

	}

	public static void manualSort(int[] intArray, int left, int right) {
		int size = right - left + 1;
		if (size <= 1)
			return;
		if (size == 2) {
			if (intArray[left] > intArray[right])
				swap(intArray, left, right);
			return;
		} else {
			if (intArray[left] > intArray[right - 1]) {
				swap(intArray, left, right - 1);

				System.out.println("");
				for (int i = 0; i < A.length; i++) {
					System.out.print(A[i]);
				}
			}
			System.out.println("");
			if (intArray[left] > intArray[right]) {
				swap(intArray, left, right);
				for (int i = 0; i < A.length; i++) {
					System.out.print(A[i]);
				}

				System.out.println("");
			}
			if (intArray[right - 1] > intArray[right]) {
				swap(intArray, right - 1, right);
				for (int i = 0; i < A.length; i++) {
					System.out.print(A[i]);
				}

				System.out.println("");
			}
			System.out.println("End Partition");
		}
	}

	private static <E extends Comparable<E>> int partition(int[] intArray, int left, int right, double pivot) {
		// precondition: A.length > 3
		int leftPtr = left;
		int rightPtr = right - 1;

		while (true) {
			while (intArray[++leftPtr] < pivot)
				;
			while (intArray[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(intArray, leftPtr, rightPtr);
		}
		swap(intArray, leftPtr, right - 1);
		return leftPtr;
	}

	public static void main(String[] args) {
		System.out.println("");
		System.out.println(" QuickSort One Beginning");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
		}

		System.out.println("");
		// quickSortTwo(A, 0, A.length - 1);
		quickSort(A);

		System.out.println("");
		System.out.println(" QuickSort One End");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
		}

		System.out.println("");
		// quickSort(A);
		// PrintStream for dump() and displayStats() method.
		// Set to null since Java complains about an uninitialized value.
		PrintStream myOut = null;
		PrintStream sysOut = new PrintStream(System.out);

		try {
			if (args.length != 6) {
				System.err.println("Expected 6 but got " + args.length);
				System.err.println("Arguments expected:");
				System.err.println("  # items to hash");
				System.err.println("  random # seed");
				System.err.println("  max load factor (e.g., 0.75)");
				System.err.println("  initial size of hash table");
				System.err.println("  max chain length (0 = no max length)");
				System.err.println("  file for output");
				System.exit(1);
			}

			int numHash = Integer.parseInt(args[0]);
			int seed = Integer.parseInt(args[1]);
			double loadFactor = Double.parseDouble(args[2]);
			// handling for bad loadFactor input

			int initSize = Integer.parseInt(args[3]);
			int maxChainLength = Integer.parseInt(args[4]);
			String outFileName = args[5];

			// Open file for output
			try {
				File outFile = new File(outFileName);
				myOut = new PrintStream(outFile);
			} catch (IOException ex) {
				System.err.println("Error opening file " + outFileName + " failed so stopping.  The error was:");
				System.err.println(ex);
				System.exit(99);
			}
			System.out.println("See " + outFileName + " file for dump output");

			myOut.println("Parameters used:");
			myOut.println("  # items to hash: " + numHash);
			myOut.println("  random # seed: " + seed);
			myOut.println("  max load factor: " + loadFactor);
			myOut.println("  initial size of hash table: " + initSize);
			myOut.println("  max chain length: " + ((maxChainLength == 0) ? "none" : maxChainLength));
			myOut.println("  output file name: " + outFileName);

			System.out.println("Parameters used:");
			System.out.println("  # items to hash: " + numHash);
			System.out.println("  random # seed: " + seed);
			System.out.println("  max load factor: " + loadFactor);
			System.out.println("  initial size of hash table: " + initSize);
			System.out.println("  max chain length: " + ((maxChainLength == 0) ? "none" : maxChainLength));
			System.out.println("  output file name: " + outFileName);

			// Do inserts into hashtable.
			HashTable<Integer> table;
			if (maxChainLength == 0)
				table = new HashTable<Integer>(initSize, loadFactor);
			else
				table = new HashTable<Integer>(initSize, loadFactor, maxChainLength);

			// It is important to give the seed so you can reproduce results.
			Random randGen = new Random(seed);
			for (int k = 0; k < numHash; k++) {
				table.insert(new Integer(randGen.nextInt()));
				// table.insert(new Integer(3 * k));

			}
			table.displayStats(myOut);
			table.dump(myOut);

			table.displayStats(sysOut);
			table.dump(sysOut); // comment this out if you don't want to see
								// the hash table contents on the console
			sysOut.flush(); // forces it to print everything it has buffered

		} catch (Throwable ex) {
			System.out.println("TestHash had unexpected and uncaught exception" + " in main");
			ex.printStackTrace();

		} finally {
			// If you don't always close the PrintStream the file may
			// appear empty.
			myOut.close();
			System.out.println("TestHash done");
			sysOut.close();
		}
	}
}
