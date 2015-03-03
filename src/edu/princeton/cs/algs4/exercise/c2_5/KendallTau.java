package edu.princeton.cs.algs4.exercise.c2_5;

import edu.princeton.cs.algs4.exercise.c2_2.Inversions;
import edu.princeton.cs.introcs.StdOut;

/*************************************************************************
 * Compilation: javac KendallTau.java Execution: java KendallTau N
 * 
 * Generate two random permutations of size N and compute their Kendall tau
 * distance (number of inversions).
 *
 *************************************************************************/

public class KendallTau {

	// return Kendall tau distance between two permutations
	public static long distance(int[] a, int[] b) {
		if (a.length != b.length) {
			throw new IllegalArgumentException("Array dimensions disagree");
		}
		int N = a.length;

		int[] ainv = new int[N];
		for (int i = 0; i < N; i++)
			ainv[a[i]] = i;

		Integer[] bnew = new Integer[N];
		for (int i = 0; i < N; i++)
			bnew[i] = ainv[b[i]];

		return Inversions.count(bnew);
	}

	// return a random permutation of size N
	public static int[] permutation(int N) {
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			int r = (int) (Math.random() * (i + 1));
			a[i] = a[r];
			a[r] = i;
		}
		return a;
	}

	public static void main(String[] args) {

		// two random permutation of size N
		int N = Integer.parseInt(args[0]);
		int[] a = KendallTau.permutation(N);
		int[] b = KendallTau.permutation(N);

		// print initial permutation
		for (int i = 0; i < N; i++)
			StdOut.println(a[i] + " " + b[i]);
		StdOut.println();

		StdOut.println("inversions = " + KendallTau.distance(a, b));
	}
}
