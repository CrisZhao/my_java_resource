package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given a linked list, remove the nth node from the end of list and return its
 * head.
 * 
 * For example,
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * 
 * Note: Given n will always be valid. Try to do this in one pass.
 * 
 */
public class RemoveNthNodeFromEndOfList {

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n < 1) {
			return head;
		}
		ListNode node1 = head;
		ListNode node2 = head;
		while (node1.next != null) {
			node1 = node1.next;
			n--;
			if (n < 0) {
				node2 = node2.next;
			}
		}
		if (n == 1) {
			head = head.next;
			return head;
		}
		node2.next = node2.next.next;

		return head;
	}

	public ListNode removeNthFromEnd1(ListNode head, int n) {
		if (n <= 0) {
			return head;
		}
		ListNode node = head;
		ArrayList<ListNode> nodes = new ArrayList<ListNode>();
		while (node != null) {
			nodes.add(node);
			node = node.next;
		}

		int index = nodes.size() - n;
		if (index == 0) {
			head = head.next;
		} else if (n == 1) {
			nodes.get(index - 1).next = null;
		} else {
			nodes.get(index - 1).next = nodes.get(index + 1);
		}
		return head;
	}

	@Test
	public void test() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		ListNode node = removeNthFromEnd(n1, 2);
		System.out.println(node);
	}
}
