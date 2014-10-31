package edu.princeton.cs.algs4.exercise.c2_1;

import java.util.Comparator;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class InsertionNotexch {

	// This class should not be instantiated.
	private InsertionNotexch() {
	}

	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			Comparable v = a[i];
			int j = i;
			for (; j > 0 && less(v, a[j - 1]); j--) {
				a[j] = a[j - 1];
			}
			a[j] = v;
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
	}

	/**
	 * Rearranges the array in ascending order, using a comparator.
	 * 
	 * @param a
	 *            the array
	 * @param c
	 *            the comparator specifying the order
	 */
	public static void sort(Object[] a, Comparator c) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			Object v = a[i];
			int j = i;
			for (; j > 0 && less(c, v, a[j - 1]); j--) {
				a[j] = a[j - 1];
			}
			a[j] = v;
			assert isSorted(a, c, 0, i);
		}
		assert isSorted(a, c);
	}

	// return a permutation that gives the elements in a[] in ascending order
	// do not change the original array a[]
	/**
	 * Returns a permutation that gives the elements in the array in ascending
	 * order.
	 * 
	 * @param a
	 *            the array
	 * @return a permutation <tt>p[]</tt> such that <tt>a[p[0]]</tt>,
	 *         <tt>a[p[1]]</tt>, ..., <tt>a[p[N-1]]</tt> are in ascending order
	 */
	public static int[] indexSort(Comparable[] a) {
		int N = a.length;
		int[] index = new int[N];
		for (int i = 0; i < N; i++)
			index[i] = i;

		for (int i = 0; i < N; i++) {
			Comparable v = a[index[i]];
			int j = i;
			for (; j > 0 && less(v, a[index[j - 1]]); j--)
				a[index[j]] = a[index[j - 1]];
			a[index[j]] = v;
		}
		return index;
	}

	/***********************************************************************
	 * Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);
	}

	// is v < w ?
	private static boolean less(Comparator c, Object v, Object w) {
		return (c.compare(v, w) < 0);
	}

	/***********************************************************************
	 * Check if array is sorted - useful for debugging
	 ***********************************************************************/
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	private static boolean isSorted(Object[] a, Comparator c) {
		return isSorted(a, c, 0, a.length - 1);
	}

	// is the array sorted from a[lo] to a[hi]
	private static boolean isSorted(Object[] a, Comparator c, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(c, a[i], a[i - 1]))
				return false;
		return true;
	}

	// print array to standard output
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

	/**
	 * Reads in a sequence of strings from standard input; insertion sorts them;
	 * and prints them to standard output in ascending order.
	 */
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Insertion.sort(a);
		show(a);
	}
}
