package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdOut;

public class RandomBag<Item> implements Iterable<Item> {
	private int N = 0;
	private Node<Item> first;

	/**
	 * Initializes an empty bag.
	 */
	public RandomBag() {
		first = null;
	}

	/**
	 * Is this bag empty?
	 * 
	 * @return true if this bag is empty; false otherwise
	 */
	public boolean isEmpty() {
		return N == 0 ? true : false;
	}

	/**
	 * Returns the number of items in this bag.
	 * 
	 * @return the number of items in this bag
	 */
	public int size() {
		return N;
	}

	/**
	 * Adds the item to this bag.
	 * 
	 * @param item
	 *            the item to add to this bag
	 */
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public static class Node<Item> {
		Item item;
		Node<Item> next;
	}

	private class RandomIterator<Item> implements Iterator<Item> {
		private int current = 0;
		private Item[] container;

		public RandomIterator(Node<Item> first) {
			if (first != null) {
				random(first);
			}
		}

		@SuppressWarnings("unchecked")
		private void random(Node<Item> first) {
			Item[] temp = (Item[]) new Object[N];
			container = (Item[]) new Object[N];
			int i = 0;
			do {
				temp[i] = first.item;
				first = first.next;
				i++;
			} while (first != null);
			for (i = 0; i < temp.length; i++) {
				int j = (i + 3) % 4;
				container[j] = temp[i];
			}
		}

		@Override
		public boolean hasNext() {
			return current < N;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = container[current];
			current++;
			return item;
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomIterator<Item>(first);
	}

	public static void main(String[] args) {
		RandomBag<String> bag = new RandomBag<String>();
		bag.add("a1");
		bag.add("a2");
		bag.add("a3");
		bag.add("a4");
		for (String s : bag) {
			StdOut.println(s);
		}
	}
}
