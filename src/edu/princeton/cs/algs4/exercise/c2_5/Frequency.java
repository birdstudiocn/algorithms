package edu.princeton.cs.algs4.exercise.c2_5;

/*************************************************************************
 *  Compilation:  javac Frequency.java
 *  Execution:    java Frequency < strings.txt
 *  
 *  Read strings from standard input and print the number of times
 *  each string occurs, in descending order.
 *
 *  % java Frequency < tale.txt
 *    7515 the
 *    4751 and
 *    4071 of
 *    3458 to
 *    2830 a
 *  ...
 *
 *************************************************************************/

import java.util.Arrays;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class Frequency {

	public static void main(String[] args) {

		// read in and sort the input strings
		String[] a = StdIn.readAllStrings();
		int N = a.length;
		Arrays.sort(a);

		// create an array of distinct strings and their frequencies
		Record[] records = new Record[N];
		String word = a[0];
		int freq = 1;
		int M = 0;
		for (int i = 1; i < N; i++) {
			if (!a[i].equals(word)) {
				records[M++] = new Record(word, freq);
				word = a[i];
				freq = 0;
			}
			freq++;
		}
		records[M++] = new Record(word, freq);

		// sort by frequency and print results
		Arrays.sort(records, 0, M);
		for (int i = M - 1; i >= 0; i--)
			StdOut.println(records[i]);
	}
}

class Record {
	private String word;
	private int freq;

	Record(String word, int freq) {
		this.word = word;
		this.freq = freq;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
}