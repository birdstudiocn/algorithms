package edu.princeton.cs.algs4.exercise.c2_1;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class ShellCSR {
	private static int compare = 0;

	public static void sort(Comparable[] a) {
		int N = a.length;

		// 3x+1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;

		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < N; i++) {
				int j;
				for (j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
			double rate = (double) compare / N;
			StdOut.printf("%d-sort compare %d rate %f \n", h, compare, rate);
			compare = 0;
			h /= 3;
		}
	}

	// exchange a[i] and a[j]
	private static void exch(Comparable[] a, int i, int j) {
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		compare++;
		return (v.compareTo(w) < 0);
	}

	public static Double[] randomInput(int N) {
		Double[] a = new Double[N];

		for (int i = 0; i < N; i++)
			a[i] = StdRandom.uniform();

		return a;
	}

	// test client
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Double[] a = randomInput(N);
		// { 1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d, 10d };
		// sort the array
		sort(a);
	}
}
