package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdOut;

public class Deque<Item> {
	private Node<Item> first;
	private Node<Item> last;
	private int N;

	public Deque() {
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0 ? true : false;
	}

	public int size() {
		return N;
	}

	public void pushLeft(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		if (isEmpty())
			last = first;
		else {
			first.next = oldfirst;
			oldfirst.prev = first;
		}
		N++;
	}

	public void pushRight(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		if (isEmpty())
			first = last;
		else {
			oldlast.next = last;
			last.prev = oldlast;
		}
		N++;
	}

	public Item popLeft() {
		if (isEmpty())
			throw new NoSuchElementException("Deque underflow");
		Node<Item> oldfirst = first;
		Item item = oldfirst.item;
		first = oldfirst.next;
		oldfirst.item = null;
		oldfirst.next = oldfirst; // help GC
		if (first == null)
			last = null;
		else
			first.prev = null;
		N--;
		return item;
	}

	public Item popRight() {
		if (isEmpty())
			throw new NoSuchElementException("Deque underflow");
		Node<Item> oldlast = last;
		Item item = oldlast.item;
		last = oldlast.prev;
		oldlast.item = null;
		oldlast.prev = oldlast; // help GC
		if (last == null)
			first = null;
		else
			last.next = null;
		N--;
		return item;
	}

	public static class Node<Item> {
		Item item;
		Node<Item> prev;
		Node<Item> next;
	}

	public static void main(String[] args) {
		Deque<String> q = new Deque<String>();
		q.pushLeft("a1");
		q.pushLeft("a2");
		q.pushRight("a3");
		StdOut.print(q.popRight() + " ");
		StdOut.print(q.popLeft() + " ");
		StdOut.println("(" + q.size() + " on Deque)");
	}
}
