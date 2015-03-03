package edu.princeton.cs.algs4.exercise.c2_5;

/*************************************************************************
 *  Compilation:  javac California.java
 *  Execution:    java California
 *  
 *  Sort names according to alphabet used in California runoff election.
 *
 *      R W Q O J M V A H B S G Z X N T C I E K U P D Y F L
 *
 *  Treats other characters as less than any uppercase letter
 *
 *
 *  % java California < california-gov.txt
 *  ROBERT "BUTCH" DOLE
 *  ROBERT C. MANNHEIM
 *  ROBERT C. NEWMAN II
 *  ROBERT CULLENBINE
 *  RONALD J. FRIEDMAN
 *  RONALD JASON PALMIERI
 *  RANDALL D. SPRAGUE
 *  RALPH A. HERNANDEZ
 *  RICH GOSSE
 *  RICHARD J. SIMMONS
 *  REVA RENEE RENZ
 *  WARREN FARRELL
 *  WILLIAM "BILL" S. CHAMBERS
 *  ...
 *  LAWRENCE STEVEN STRAUSS
 *  LINGEL H. WINTERS
 *  LEO GALLAGHER
 *  LEONARD PADILLA
 *
 *  Data file:  http://www.cs.princeton.edu/algs4/35applications/california-gov.txt
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class California {
	public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();

	private static class CandidateComparator implements Comparator<String> {
		private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";

		@Override
		public int compare(String a, String b) {
			if (a == b)
				return 0;
			int n = Math.min(a.length(), b.length());
			for (int i = 0; i < n; i++) {
				int aindex = order.indexOf(a.charAt(i));
				int bindex = order.indexOf(b.charAt(i));
				if (aindex < bindex)
					return -1;
				else if (aindex > bindex)
					return +1;
			}
			return a.length() - b.length();
		}
	}

	// test client
	public static void main(String[] args) {
		String[] candidates = StdIn.readAll().toUpperCase().split("\\n+");
		int N = candidates.length;
		Arrays.sort(candidates, California.CANDIDATE_ORDER);
		for (int i = 0; i < N; i++)
			StdOut.println(candidates[i]);
	}

}
