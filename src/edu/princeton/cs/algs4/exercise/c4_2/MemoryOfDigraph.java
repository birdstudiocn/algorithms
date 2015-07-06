package edu.princeton.cs.algs4.exercise.c4_2;

/*************************************************************************
 *  Compilation:  javac -cp .:jama.jar:classmexer.jar MemoryOfDigraph.java
 *  Execution:    java  -cp .:jama.jar:classmexer.jar -XX:-UseCompressedOops -javaagent:classmexer.jar MemoryOfDigraph
 *  Dependencies: Digraph.java MultipleLinearRegression.java StdOut.java classmexer.jar jama.jar
 *
 *  % java -cp .:jama.jar:classmexer.jar -XX:-UseCompressedOops -javaagent:classmexer.jar MemoryOfDigraph
 *  memory of a Digraph with V vertices and E edges:
 *  56.00 + 40.00 V + 64.00 E bytes (R^2 = 1.000)
 *
 *************************************************************************/

import com.javamex.classmexer.MemoryUtil;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.MultipleLinearRegression;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class MemoryOfDigraph {

	public static void main(String[] args) {

		Integer a = new Integer(123456);
		StdOut.println("size of Integer = " + MemoryUtil.memoryUsageOf(a)
				+ " bytes");

		int N = 40;
		int[] V = new int[N];
		int[] E = new int[N];

		// build random graphs and compute memory usage
		long[] memory = new long[N];
		for (int i = 0; i < N; i++) {
			V[i] = 128 + 2 * StdRandom.uniform(500); // vertices
			E[i] = V[i] * StdRandom.uniform(10); // edges
			Digraph G = new Digraph(V[i]);
			for (int j = 0; j < E[i]; j++) {
				// first 128 Integer values are cached, so don't use these
				int v = 128 + StdRandom.uniform(V[i] - 128);
				int w = 128 + StdRandom.uniform(V[i] - 128);
				G.addEdge(v, w);
			}
			memory[i] = MemoryUtil.deepMemoryUsageOf(G);
		}

		// build multiple linear regression coefficients
		double[] y = new double[N];
		for (int i = 0; i < N; i++) {
			y[i] = memory[i];
		}

		double[][] x = new double[N][3];
		for (int i = 0; i < N; i++) {
			x[i][0] = 1.0;
			x[i][1] = V[i];
			x[i][2] = E[i];
		}

		MultipleLinearRegression regression = new MultipleLinearRegression(x, y);
		StdOut.println("memory of a Digraph with V vertices and E edges:");
		StdOut.printf("%.2f + %.2f V + %.2f E bytes (R^2 = %.3f)\n",
				regression.beta(0), regression.beta(1), regression.beta(2),
				regression.R2());
	}

}
