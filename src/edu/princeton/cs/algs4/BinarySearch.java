package edu.princeton.cs.algs4;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
/*************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch whitelist.txt < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *  
 *************************************************************************/
import java.util.Arrays;

/**
 * The <tt>BinarySearch</tt> class provides a static method for binary searching
 * for an integer in a sorted array of integers.
 * <p>
 * The <em>rank</em> operations takes logarithmic time in the worst case.
 * <p>
 * For additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class BinarySearch {

	/**
	 * This class should not be instantiated.
	 */
	private BinarySearch() {
	}

	/**
	 * Searches for the integer key in the sorted array a[].
	 * 
	 * @param key
	 *            the search key
	 * @param a
	 *            the array of integers, must be sorted in ascending order
	 * @return index of key in array a[] if present; -1 if not present
	 */
	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	/**
	 * Reads in a sequence of integers from the whitelist file, specified as a
	 * command-line argument. Reads in integers from standard input and prints
	 * to standard output those integers that do *not* appear in the file.
	 */
	public static void main(String[] args) {

		// read the integers from a file
		In in = new In(args[0]);
		int[] whitelist = in.readAllInts();

		// sort the array
		Arrays.sort(whitelist);

		// read integer key from standard input; print if not in whitelist
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (rank(key, whitelist) == -1)
				StdOut.println(key);
		}
	}
}

/*************************************************************************
 * Copyright 2002-2012, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4-package.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4-package.jar is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * algs4-package.jar is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * algs4-package.jar. If not, see http://www.gnu.org/licenses.
 *************************************************************************/

