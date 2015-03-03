package edu.princeton.cs.algs4.exercise.c2_5;

/*************************************************************************
 *  Compilation:  javac LPT.java
 *  Execution:    java LPT M < jobs.txt
 *  Dependencies: Processor.java Job.java
 *  
 *  Find an approximate solution to the load balancing
 *  problem using the LPT (longest processing time first) rule.
 *
 *************************************************************************/

import java.util.Arrays;

import javax.annotation.processing.Processor;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class LPT {

	public static void main(String[] args) {
		int M = Integer.parseInt(args[0]); // number of machines

		int N = StdIn.readInt();
		Job[] jobs = new Job[N];
		for (int i = 0; i < N; i++) {
			String name = StdIn.readString();
			double time = StdIn.readDouble();
			jobs[i] = new Job(name, time);
		}

		// sort jobs in ascending order of processing time
		Arrays.sort(jobs);

		// generate M empty processors and put on priority queue
		MinPQ<Processor> pq = new MinPQ<Processor>(M);
		for (int i = 0; i < M; i++)
			pq.insert(new Processor());

		// assign each job (in decreasing order of time) to processor that is
		// least busy
		for (int j = N - 1; j >= 0; j--) {
			Processor min = pq.delMin();
			min.add(jobs[j]);
			pq.insert(min);
		}

		// print out contents of each processor
		while (!pq.isEmpty())
			StdOut.println(pq.delMin());
	}

}
