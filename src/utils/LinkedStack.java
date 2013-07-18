package utils;

import org.junit.Test;

public class LinkedStack<T> {
	private class Node<U> {
		U item;
		Node<U> next;

		Node() {
			item = null;
			next = null;
		}

		Node(U item, Node<U> next) {
			this.item = item;
			this.next = next;
		}

		boolean end() {
			return item == null && next == null;
		}
	}

	private Node<T> top = new Node<T>();

	public void push(T item) {
		top = new Node<T>(item, top);
	}

	public T pop() {
		T result = top.item;
		if (!top.end()) {
			top = top.next;
		}
		return result;
	}
	
	
	
	@Test
	public void test() {
		LinkedStack<String> tt = new LinkedStack<String>();
		for (String s : "phasers on stun!".split(" ")) {
			tt.push(s);
		}
		String s;
		while((s = tt.pop()) !=null)
			System.out.println(s);
	}

}
