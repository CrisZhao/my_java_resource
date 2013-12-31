package leetcodeoj;

/**
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 * 
 * Follow up: Can you solve it without using extra space?
 * 
 * 
 */
public class LinkedListCycleII {
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && slow != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (fast == slow) {
				break;
			}
		}
		if(fast==null||fast.next==null) {
			return null;
		}
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return fast;
	}
}
