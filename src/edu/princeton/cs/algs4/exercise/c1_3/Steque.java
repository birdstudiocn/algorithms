package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdOut;

public class Steque<Item> {
	Node<Item> first;

	public void push(Item item) {
		if (first == null) {
			first = new Node<Item>();
			first.item = item;
		} else {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
		}
	}

	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException("Steque underflow");
		Item returnvalue = first.item;
		first = first.next;
		return returnvalue;
	}

	public void enqueue(Item item) {
		if (first == null) {
			first = new Node<Item>();
			first.item = item;
		} else {
			Node<Item> current = first;
			Node<Item> next = first.next;
			while (next != null) {
				current = next;
				next = next.next;
			}
			next = new Node<Item>();
			next.item = item;
			current.next = next;
		}
	}

	public boolean isEmpty() {
		return first == null;
	}

	private static class Node<Item> {
		Item item;
		Node<Item> next;
	}

	public static void main(String[] args) {
		Steque<String> sq = new Steque<String>();
		sq.push("a1");
		sq.enqueue("a3");
		sq.enqueue("a4");
		sq.push("a2");

		StdOut.println(sq.pop());
		StdOut.println(sq.pop());
		StdOut.println(sq.pop());
		StdOut.println(sq.pop());
	}

}
