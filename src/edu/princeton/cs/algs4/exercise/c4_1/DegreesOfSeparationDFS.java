package edu.princeton.cs.algs4.exercise.c4_1;

import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/*************************************************************************
 * Compilation: javac DegreesOfSeparationDFS.java Execution: java
 * DegreesOfSeparationDFS filename delimiter source Dependencies:
 * SymbolGraph.java Graph.java DepthFirstPaths.java StdOut.java Data files:
 * http://algs4.cs.princeton.edu/41undirected/routes.txt
 * http://algs4.cs.princeton.edu/41undirected/movies.txt
 * 
 * 
 * % java DegreesOfSeparationDFS movies.txt "/" "Bacon, Kevin" Kidman, Nicole
 * Bacon, Kevin Woodsman, The (2004) Sedgwick, Kyra Something to Talk About
 * (1995) Gillan, Lisa Roberts Runaway Bride (1999) Schertler, Jean ... [1782
 * movies ] (!) Eskelson, Dana Interpreter, The (2005) Silver, Tracey (II)
 * Copycat (1995) Chua, Jeni Metro (1997) Ejogo, Carmen Avengers, The (1998)
 * Atkins, Eileen Hours, The (2002) Kidman, Nicole
 *
 *************************************************************************/

public class DegreesOfSeparationDFS {
	public static void main(String[] args) {
		String filename = args[0];
		String delimiter = args[1];
		String source = args[2];

		// StdOut.println("Source: " + source);

		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph G = sg.G();
		if (!sg.contains(source)) {
			StdOut.println(source + " not in database.");
			return;
		}

		int s = sg.index(source);
		DepthFirstPaths bfs = new DepthFirstPaths(G, s);

		while (!StdIn.isEmpty()) {
			String sink = StdIn.readLine();
			if (sg.contains(sink)) {
				int t = sg.index(sink);
				if (bfs.hasPathTo(t)) {
					for (int v : bfs.pathTo(t)) {
						StdOut.println("   " + sg.name(v));
					}
				} else {
					StdOut.println("Not connected");
				}
			} else {
				StdOut.println("   Not in database.");
			}
		}
	}
}
