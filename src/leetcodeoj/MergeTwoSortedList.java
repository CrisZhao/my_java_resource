package leetcodeoj;

import org.junit.Test;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * @author Cris Zhao
 * 
 */
public class MergeTwoSortedList {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode f = l1;
		ListNode s = l2;
		ListNode result = new ListNode(-1);
		ListNode index = result;
		while (f != null && s != null) {
			if (f.val <= s.val) {
				index.next = f;
				f = f.next;
			} else {
				index.next = s;
				s = s.next;
			}
			index = index.next;
		}
		if (f == null) {
			index.next = s;
		}
		if (s == null) {
			index.next = f;
		}

		return result.next;
	}

	@Test
	public void test() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(3);
		l1.next.next = new ListNode(4);
		ListNode l2 = new ListNode(2);
		l2.next = new ListNode(4);
		l2.next.next = new ListNode(6);
		ListNode result = mergeTwoLists(l1, l2);
		System.out.println(result);

	}
}
