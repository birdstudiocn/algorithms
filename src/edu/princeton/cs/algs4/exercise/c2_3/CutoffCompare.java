package edu.princeton.cs.algs4.exercise.c2_3;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class CutoffCompare {
	public static double time(String alg, Comparable[] a, int c) {
		Stopwatch timer = new Stopwatch();

		if (alg.equals("QuickX"))
			QuickX.sort(a, c);

		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, int N, int T, int c) {
		double total = 0.0;
		Double[] a = new Double[N];
		// Double[] a = { 10d, 9d, 8d, 7d, 6d, 5d, 4d, 3d, 2d, 1d, 0d };
		// Double[] a = { 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d, 11d };
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++)
				a[i] = StdRandom.uniform();
			total += time(alg, a, c);
		}
		return total;
	}

	public static void main(String[] args) {
		String alg1 = args[0];
		// String alg2 = args[1];
		int N = Integer.parseInt(args[1]);
		int T = Integer.parseInt(args[2]);
		for (int i = 1; i <= 30; i++) {
			double t1 = timeRandomInput(alg1, N, T, i);
			// double t2 = timeRandomInput(alg2, N, T);
			StdOut.printf("CUTOFF is " + i);
			StdOut.printf(" %s is %f \n", alg1, t1);
		}
		// StdOut.printf("%s is %f \n", alg2, t2);
		// StdOut.printf("For %d random Doubles\n %s is", N, alg1);
		// StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
	}

	static class QuickX {
		// private static final int CUTOFF = 8; // cutoff to insertion sort,
		// must be
		// >=
		// 1

		// This class should not be instantiated.
		private QuickX() {
		}

		/**
		 * Rearranges the array in ascending order, using the natural order.
		 * 
		 * @param a
		 *            the array to be sorted
		 */
		public static void sort(Comparable[] a, int CUTOFF) {
			sort(a, 0, a.length - 1, CUTOFF);
		}

		private static void sort(Comparable[] a, int lo, int hi, int CUTOFF) {
			int N = hi - lo + 1;

			// cutoff to insertion sort
			if (N <= CUTOFF) {
				insertionSort(a, lo, hi);
				return;
			}

			// use median-of-3 as partitioning element
			else if (N <= 40) {
				int m = median3(a, lo, lo + N / 2, hi);
				exch(a, m, lo);
			}

			// use Tukey ninther as partitioning element
			else {
				int eps = N / 8;
				int mid = lo + N / 2;
				int m1 = median3(a, lo, lo + eps, lo + eps + eps);
				int m2 = median3(a, mid - eps, mid, mid + eps);
				int m3 = median3(a, hi - eps - eps, hi - eps, hi);
				int ninther = median3(a, m1, m2, m3);
				exch(a, ninther, lo);
			}

			// Bentley-McIlroy 3-way partitioning
			int i = lo, j = hi + 1;
			int p = lo, q = hi + 1;
			Comparable v = a[lo];
			while (true) {
				while (less(a[++i], v))
					if (i == hi)
						break;
				while (less(v, a[--j]))
					if (j == lo)
						break;

				// pointers cross
				if (i == j && eq(a[i], v))
					exch(a, ++p, i);
				if (i >= j)
					break;

				exch(a, i, j);
				if (eq(a[i], v))
					exch(a, ++p, i);
				if (eq(a[j], v))
					exch(a, --q, j);
			}

			i = j + 1;
			for (int k = lo; k <= p; k++)
				exch(a, k, j--);
			for (int k = hi; k >= q; k--)
				exch(a, k, i++);

			sort(a, lo, j, CUTOFF);
			sort(a, i, hi, CUTOFF);
		}

		// sort from a[lo] to a[hi] using insertion sort
		private static void insertionSort(Comparable[] a, int lo, int hi) {
			for (int i = lo; i <= hi; i++)
				for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
					exch(a, j, j - 1);
		}

		// return the index of the median element among a[i], a[j], and a[k]
		private static int median3(Comparable[] a, int i, int j, int k) {
			return (less(a[i], a[j]) ? (less(a[j], a[k]) ? j
					: less(a[i], a[k]) ? k : i) : (less(a[k], a[j]) ? j : less(
					a[k], a[i]) ? k : i));
		}

		/***********************************************************************
		 * Helper sorting functions
		 ***********************************************************************/

		// is v < w ?
		private static boolean less(Comparable v, Comparable w) {
			return (v.compareTo(w) < 0);
		}

		// does v == w ?
		private static boolean eq(Comparable v, Comparable w) {
			return (v.compareTo(w) == 0);
		}

		// exchange a[i] and a[j]
		private static void exch(Object[] a, int i, int j) {
			Object swap = a[i];
			a[i] = a[j];
			a[j] = swap;
		}

		/***********************************************************************
		 * Check if array is sorted - useful for debugging
		 ***********************************************************************/
		private static boolean isSorted(Comparable[] a) {
			for (int i = 1; i < a.length; i++)
				if (less(a[i], a[i - 1]))
					return false;
			return true;
		}

		// print array to standard output
		private static void show(Comparable[] a) {
			for (int i = 0; i < a.length; i++) {
				StdOut.println(a[i]);
			}
		}
	}
}
