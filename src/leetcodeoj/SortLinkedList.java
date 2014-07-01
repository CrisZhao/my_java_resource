package leetcodeoj;

import org.junit.Test;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortLinkedList {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode first = head;
        ListNode end = head;
        ListNode index = head.next;
        ListNode start = head;
        ListNode temp = head;
        ListNode tmp = null;
        int value = head.val;
        while(index!=null) {
            if(index.val<value) {

                tmp = index.next;
                index.next=start;
                start=index;
                index=tmp;
                end.next=index;
            } else if (index.val==value){
                end=index;
                index=index.next;
            } else{
                index=index.next;
            }
        }
        return start;
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListNode node = sortList(n1);
        System.out.println(node);
    }
}
