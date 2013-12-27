package leetcodeoj;

import org.junit.Test;


/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example, Given 1->1->2, return 1->2. 
 * Given 1->1->2->3->3, return 1->2->3.
 * 
 * 
 */
public class RemoveDuplicates {

	public ListNode deleteDuplicates(ListNode head) {
		if(head==null) {
			return head;
		}
		ListNode p = head;
		ListNode q = head.next;
		while (q!=null) {
			if (p.val==q.val) {
				p.next=q.next;
				q=q.next;
			} else {
				p=p.next;
				q=q.next;
			}
		}
		return head;
	}
	
	@Test
	public void test() {
		ListNode node = new ListNode(1);
		node.next=new ListNode(1);
		node.next.next=new ListNode(2);
		deleteDuplicates(node);
		System.out.println(node);
	}
}
