package leetcodeoj;

import org.junit.Test;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * 
 */
public class SwapPairs {
	public ListNode swapPairs(ListNode head) {
		ListNode node = head;
		while (node != null && node.next != null) {
			int temp = node.val;
			node.val = node.next.val;
			node.next.val = temp;
			node = node.next.next;
		}
		return head;
	}

	public ListNode recursion(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		int temp = head.val;
		head.val = head.next.val;
		head.next.val = temp;
		swapPairs(head.next.next);
		return head;
	}

	@Test
	public void test() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		swapPairs(n1);
		System.out.println(n1);
	}

}
