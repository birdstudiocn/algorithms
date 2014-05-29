package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.Iterator;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class FixedCapacityStackOfStrings implements Iterable<String> {
	private final String[] a; // holds the items
	private int N; // number of items in stack

	// create an empty stack with given capacity
	public FixedCapacityStackOfStrings(int capacity) {
		a = new String[capacity];
	}

	public boolean isEmpty() {
		return (N == 0);
	}

	public boolean isFull() {
		return (N == a.length);
	}

	public void push(String item) {
		a[N++] = item;
	}

	public String pop() {
		return a[--N];
	}

	public String peek() {
		return a[N - 1];
	}

	@Override
	public Iterator<String> iterator() {
		return new ReverseArrayIterator();
	}

	public class ReverseArrayIterator implements Iterator<String> {
		private int i = N - 1;

		@Override
		public boolean hasNext() {
			return i >= 0;
		}

		@Override
		public String next() {
			return a[i--];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		int max = Integer.parseInt(args[0]);
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(max);
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				stack.push(item);
			else if (stack.isEmpty())
				StdOut.println("BAD INPUT");
			else
				StdOut.print(stack.pop() + " ");
		}
		StdOut.println();

		// print what's left on the stack
		StdOut.print("Left on stack: ");
		for (String s : stack) {
			StdOut.print(s + " ");
		}
		StdOut.println();
	}
}