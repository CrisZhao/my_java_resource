package leetcodeoj;

import org.junit.Test;

/**
 * Given a binary tree
 * 
 * struct TreeLinkNode {
 *     TreeLinkNode *left;
 *     TreeLinkNode *right;
 *     TreeLinkNode *next; }
 *    
 * Populate each next pointer to point to its next right node. If there
 * is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space. You may assume that it is a perfect
 * binary tree (ie, all leaves are at the same level, and every parent has two
 * children). 
 * 
 * For example, Given the following perfect binary tree,
 *       1
 *      /  \
 *     2    3
 *    / \  / \
 *   4  5  6  7
 * After calling your function, the tree should look like:
 *       1 -> NULL
 *      /  \
 *     2 -> 3 -> NULL
 *    / \  / \
 *   4->5->6->7 -> NULL
 * 
 * 
 */
public class NextRightPointer {
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		TreeLinkNode l = root.left;
		TreeLinkNode r = root.right;

		while (l != null) {
			l.next = r;
			l = l.right;
			r = r.left;
		}
		connect(root.left);
		connect(root.right);
	}

	@Test
	public void test() {
		TreeLinkNode root = new TreeLinkNode(1);
		TreeLinkNode n2 = new TreeLinkNode(2);
		TreeLinkNode n3 = new TreeLinkNode(3);
		TreeLinkNode n4 = new TreeLinkNode(4);
		TreeLinkNode n5 = new TreeLinkNode(5);
		TreeLinkNode n6 = new TreeLinkNode(6);
		TreeLinkNode n7 = new TreeLinkNode(7);
		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		connect(root);
		System.out.println(root);

	}

}
