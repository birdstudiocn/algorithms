package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdOut;

public class ResizingArrayDeque<Item> {
	private int N = 0; // number of elements on queue
	private int first = 0; // index of first element of queue
	private int last = 0; // index of next available slot
	private Item[] container;

	@SuppressWarnings("unchecked")
	public ResizingArrayDeque() {
		container = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		return N == 0 ? true : false;
	}

	public int size() {
		return N;
	}

	public void pushLeft(Item item) {
		if (N == container.length)
			resize(container.length * 2);
		if (N == 0)
			container[first] = item;
		else {
			if (first == 0)
				offsetRight(1);
			container[--first] = item;
		}
		N++;
	}

	public void pushRight(Item item) {
		if (N == container.length)
			resize(container.length * 2);
		container[last++] = item;
		N++;
	}

	public Item popLeft() {
		if (isEmpty())
			throw new NoSuchElementException("ResizingArrayDeque underflow");
		Item item = container[first];
		container[first] = null;
		first++;
		N--;
		if (N > 0 && N * 4 == container.length)
			resize(container.length / 2);
		return item;
	}

	public Item popRight() {
		if (isEmpty())
			throw new NoSuchElementException("ResizingArrayDeque underflow");
		Item item = container[--last];
		container[last] = null;
		N--;
		if (N > 0 && N * 4 == container.length)
			resize(container.length / 2);
		return item;
	}

	@SuppressWarnings("unchecked")
	private void resize(int max) {
		assert max >= N;
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = container[(first + i) % container.length];
		}
		container = temp;
		first = 0;
		last = N;
	}

	@SuppressWarnings("unchecked")
	private void offsetRight(int pos) {
		Item[] temp = (Item[]) new Object[container.length];
		for (int i = first; i < N; i++) {
			temp[i + pos] = container[i];
			if (i == first) {
				last++;
				first++;
			}
		}
		container = temp;
	}

	public static void main(String[] args) {
		ResizingArrayDeque<String> q = new ResizingArrayDeque<String>();
		q.pushRight("a1");
		q.pushLeft("a2");
		q.pushRight("a3");
		StdOut.print(q.popRight() + " ");
		StdOut.print(q.popLeft() + " ");
		StdOut.println("(" + q.size() + " on ResizingArrayDeque)");
		q.pushRight("a4");
		q.pushRight("a5");
		q.pushRight("a6");
		StdOut.print(q.popLeft() + " ");
		StdOut.print(q.popLeft() + " ");
		StdOut.print(q.popLeft() + " ");
		StdOut.print(q.popLeft() + " ");
		StdOut.println("(" + q.size() + " on ResizingArrayDeque)");
		q.pushRight("a7");
		q.pushLeft("a8");
		q.pushRight("a9");
		StdOut.print(q.popRight() + " ");
		StdOut.print(q.popLeft() + " ");
		StdOut.println("(" + q.size() + " on ResizingArrayDeque)");
	}
}
