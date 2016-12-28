package project4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

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
 * This class implements a hashtable that using chaining for collision handling.
 * Any non-<tt>null</tt> item may be added to a hashtable. Chains are
 * implemented using <tt>LinkedList</tt>s. When a hashtable is created, its
 * initial size, maximum load factor, and (optionally) maximum chain length are
 * specified. The hashtable can hold arbitrarily many items and resizes itself
 * whenever it reaches its maximum load factor or whenever it reaches its
 * maximum chain length (if a maximum chain length has been specified).
 * 
 * Note that the hashtable allows duplicate entries.
 * 
 * @author Charles Fischer
 * @author Katrina Van Laan,
 */

public class HashTable<T> {
	LinkedList<T>[] table;
	double load;
	int maxChain = 0;// initializing to no set
	int numItems = 0;// initializing to 0

	/**
	 * Constructs an empty hashtable with the given initial size, maximum load
	 * factor, and no maximum chain length. The load factor should be a real
	 * number greater than 0.0 (not a percentage). For example, to create a hash
	 * table with an initial size of 10 and a load factor of 0.85, one would
	 * use:
	 * 
	 * <dir><tt>HashTable ht = new HashTable(10, 0.85);</tt></dir>
	 *
	 * @param initSize
	 *            the initial size of the hashtable.
	 * @param loadFactor
	 *            the load factor expressed as a real number.
	 * @throws IllegalArgumentException
	 *             if <tt>initSize</tt> is less than or equal to 0 or if
	 *             <tt>loadFactor</tt> is less than or equal to 0.0
	 **/
	public HashTable(int initSize, double loadFactor) throws IllegalArgumentException {
		if (loadFactor <= 0.0 || initSize <= 0) {// handling for bad values;
			throw new IllegalArgumentException();
		} else {
			load = loadFactor;// setting loadFactor
			table = new LinkedList[initSize];// setting initSize;
		}
	}

	/**
	 * Constructs an empty hashtable with the given initial size, maximum load
	 * factor, and maximum chain length. The load factor should be a real number
	 * greater than 0.0 (and not a percentage). For example, to create a hash
	 * table with an initial size of 10, a load factor of 0.85, and a maximum
	 * chain length of 20, one would use:
	 * 
	 * <dir><tt>HashTable ht = new HashTable(10, 0.85, 20);</tt></dir>
	 *
	 * @param initSize
	 *            the initial size of the hashtable.
	 * @param loadFactor
	 *            the load factor expressed as a real number.
	 * @param maxChainLength
	 *            the maximum chain length.
	 * @throws IllegalArgumentException
	 *             if <tt>initSize</tt> is less than or equal to 0 or if
	 *             <tt>loadFactor</tt> is less than or equal to 0.0 or if
	 *             <tt>maxChainLength</tt> is less than or equal to 0.
	 **/
	public HashTable(int initSize, double loadFactor, int maxChainLength) throws IllegalArgumentException {
		if (loadFactor <= 0.0 || initSize <= 0 || maxChainLength <= 0) {
			throw new IllegalArgumentException();
		} else {
			load = loadFactor;
			table = new LinkedList[initSize];
			maxChain = maxChainLength;
		}
	}

	/**
	 * Determines if the given item is in the hashtable and returns it if
	 * present. If more than one copy of the item is in the hashtable, the first
	 * copy encountered is returned.
	 *
	 * @param item
	 *            the item to search for in the hashtable.
	 * @return the item if it is found and <tt>null</tt> if not found.
	 **/
	public T lookup(T item) {

		// creating key
		int key = item.hashCode();
		key = key % table.length;
		if (key < 0) {
			key += table.length;
		}

		if (table[key] == null) {
			return null;// is empty, item is not present
		} else {
			LinkedList<T> keyList = table[key];
			for (int i = 0; i < keyList.size(); i++) {
				T curr = keyList.get(i);
				if (curr.equals(item)) {
					return item;
				}
			}
			return null;// if not found, item is not present
		}
	}

	/**
	 * Inserts the given item into the hashtable. The item cannot be
	 * <tt>null</tt>. If there is a collision, the item is added to the end of
	 * the chain.
	 * <p>
	 * If the load factor of the hashtable after the insert would exceed (not
	 * equal) the maximum load factor (given in the constructor), then the
	 * hashtable is resized.
	 * 
	 * If the maximum chain length of the hashtable after insert would exceed
	 * (not equal) the maximum chain length (given in the constructor), then the
	 * hashtable is resized.
	 * 
	 * When resizing, to make sure the size of the table is reasonable, the new
	 * size is always 2 x <i>old size</i> + 1. For example, size 101 would
	 * become 203. (This guarantees that it will be an odd size.)
	 * </p>
	 * <p>
	 * Note that duplicates <b>are</b> allowed.
	 * </p>
	 *
	 * @param item
	 *            the item to add to the hashtable.
	 * @throws NullPointerException
	 *             if <tt>item</tt> is <tt>null</tt>.
	 **/

	public void insert(T item) throws NullPointerException {
		if (item == null) {// handling
			throw new NullPointerException();
		}
		// calculating key
		int key = item.hashCode();
		key = key % table.length;
		if (key < 0) {
			key += table.length;
		}
		insertHelp(key, item);// pass to helper function
		// if load is met/exceeded, expand table
		if ((numItems / table.length) >= load) {
			expandArray();
		}
	}

	/**
	 * Helper function for insert method
	 *
	 * @param item
	 *            the item to add to the hashtable.
	 * @param key
	 *            the key for this item
	 * 
	 **/
	private void insertHelp(int key, T item) {
		if (table[key] == null) {
			table[key] = new LinkedList<T>();
		}
		if (maxChain != 0) {// if maxChain is set
			if (table[key].size() < maxChain) {
				table[key].add(item);
				numItems++;
			} else {// if maxChain is exceeded, expandArray to take care of
					// this;
				expandArray();
				insert(item);// try inserting again
			}
		} else {
			table[key].add(item);
			numItems++;
		}
	}

	/**
	 * Expands the table if the maximum load factor is met. Then is rehashes the
	 * table. Iterating through the entire table and assigning new keys to the
	 * values.
	 **/
	private void expandArray() {
		// newArray
		LinkedList<T>[] newArray = new LinkedList[(table.length * 2) + 1];
		int newLength = newArray.length; // new length for making new keys
		// rehashing all values in new table;
		for (int k = 0; k < table.length; k++) {
			LinkedList<T> list = table[k];
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					T curr = list.get(i);
					// make new keys
					int newKey = curr.hashCode();
					newKey = newKey % newLength;
					if (newKey < 0) {
						newKey += newLength;
					}

					if (newArray[newKey] != null) {
						newArray[newKey].add(curr);
					} else {
						newArray[newKey] = new LinkedList<T>();
						newArray[newKey].add(curr);
					}
				}

			}
		} // set table field to new table;
		table = newArray;
	}

	/**
	 * Removes and returns the given item from the hashtable. If the item is not
	 * in the hashtable, <tt>null</tt> is returned. If more than one copy of the
	 * item is in the hashtable, only the first copy encountered is removed and
	 * returned.
	 *
	 * @param item
	 *            the item to delete in the hashtable.
	 * @return the removed item if it was found and <tt>null</tt> if not found.
	 **/

	public T delete(T item) {
		// make key
		int key = item.hashCode();
		key = key % table.length;
		if (key < 0) {
			key += table.length;
		}
		// search
		if (table[key] != null) {
			LinkedList<T> keyList = table[key];
			if (keyList.contains(item)) {
				keyList.remove(item);
				return item;
			}
		}
		return null;
	}

	/**
	 * Prints all the items in the hashtable to the <tt>PrintStream</tt>
	 * supplied. The items are printed in the order determined by the index of
	 * the hashtable where they are stored (starting at 0 and going to (table
	 * size - 1)). The values at each index are printed according to the order
	 * in the <tt>LinkedList</tt> starting from the beginning.
	 *
	 * @param out
	 *            the place to print all the output.
	 **/
	public void dump(PrintStream out) {
		out.println("Hashtable contents:");
		int i = 0;
		for (LinkedList<T> key : table) {
			if (key != null) {
				out.print(i + ": ");
				out.println(key);
			}
			i++;
		}
	}

	/**
	 * Prints statistics about the hashtable to the <tt>PrintStream</tt>
	 * supplied. The statistics displayed are:
	 * <ul>
	 * <li>the current table size
	 * <li>the number of items currently in the table
	 * <li>the current load factor
	 * <li>the length of the largest chain
	 * <li>the number of chains of length 0
	 * <li>the average length of the chains of length > 0
	 * </ul>
	 *
	 * @param out
	 *            the place to print all the output.
	 **/
	public void displayStats(PrintStream out) {
		out.println("Hashtable statistics:");
		out.println("  current table size:       " + table.length);
		out.println("  # items in table:         " + numItems);
		// calculating load factor, converting to doubles
		double l = table.length;
		double n = numItems;
		out.println("  current load factor:      " + n / l);
		ArrayList<Integer> lengthArr = new ArrayList<Integer>();
		// calculating for remaining values;
		double lengthAv = 0;
		double numChains = 0;
		int numZero = 0;

		for (LinkedList<T> key : table) {
			if (key != null) {
				numChains++;
				// creating array of chain lengths;
				lengthArr.add(key.size());
				// adding chain lengths for average calculation
				lengthAv += key.size();
			} else {
				// if list is empty, incrementing number of empty chains
				numZero++;
			}
		}
		// grabbing 'longest chain length'
		Collections.sort(lengthArr); // sort array of chain lengths
		int max = lengthArr.get(lengthArr.size() - 1);// largest is last value;

		out.println("  longest chain length:     " + max);
		out.println("  # 0-length chains:        " + numZero);
		out.println("  avg (non-0) chain length: " + lengthAv / numChains);

	}

}