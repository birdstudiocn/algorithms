package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdOut;

public class LinkedList<Item> implements Iterable<Item> {
	Node first;
	int size;

	public LinkedList(Item item) {
		first = new Node();
		first.item = item;
		size++;
	}

	public void add(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}

	public void delete(int i) {
		if (i < 1 || i > size)
			throw new NoSuchElementException("LinkedList underflow");
		Iterator<Item> iterator = iterator();
		int count = 1;
		if (i == 1)
			first = first.next;
		else {
			Node current = first;
			while (iterator.hasNext()) {
				Node next = current.next;
				if ((count + 1) == i) {
					current.next = next.next;
					size--;
					break;
				} else
					current = next;
				count++;
			}
		}
	}

	public boolean find(Item key) {
		for (Item item : this) {
			if (item.equals(key))
				return true;
		}
		return false;
	}

	@Override
	public Iterator<Item> iterator() {
		LinkedIterator iterator = new LinkedIterator();
		return iterator;
	}

	private class LinkedIterator implements Iterator<Item> {
		private int i = size;
		private Node current = first;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item returnValue = current.item;
			current = current.next;
			i--;
			return returnValue;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Node {
		Item item;
		Node next;
	}

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>("a1");
		list.add("a2");
		list.add("a3");
		list.add("a4");
		list.add("a5");
		list.add("a6");
		list.add("a7");
		list.add("a8");
		list.add("a9");
		list.add("a10");
		list.delete(10);

		for (String s : list)
			StdOut.print(s + " ");
		StdOut.println();
		StdOut.println(list.find("a5"));
	}

}
