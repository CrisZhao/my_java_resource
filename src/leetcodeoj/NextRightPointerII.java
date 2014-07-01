package leetcodeoj;

import org.junit.Test;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution
 * still work?
 * 
 * Note:
 * 
 * You may only use constant extra space. For example, Given the following
 * binary tree,
 * 
 *      1 
 *     / \ 
 *    2   3 
 *   / \   \ 
 *  4   5   7
 * 
 * After calling your function, the tree should look like:
 * 
 *       1-> NULL 
 *      / \ 
 *     2-> 3 -> NULL 
 *    / \   \ 
 *   4-> 5-> 7 -> NULL
 * 
 * 
 */
public class NextRightPointerII {

	public void connect(TreeLinkNode root) {
		TreeLinkNode node = root;
		while (node != null) {

			TreeLinkNode firstNextLevel = null;
			TreeLinkNode p = node;
			TreeLinkNode pre = null;
			while (p != null) {
				if (firstNextLevel == null) {
					firstNextLevel = p.left == null ? p.right : p.left;
				}
				if (p.left != null) {
					if (pre != null) {
						pre.next = p.left;
					}
					pre = p.left;
				}
				if (p.right != null) {
					if (pre != null) {
						pre.next = p.right;
					}
					pre = p.right;
				}
				p = p.next;
			}
			node = firstNextLevel;
		}
	}

	public void connect1(TreeLinkNode root) {
		if (root == null) {
			return;
		}
		TreeLinkNode p = root;
		TreeLinkNode parent = root.next;
		if (p.right != null && p.left != null) {
			p.left.next = p.right;
			p = p.right;
		} else if (p.right != null) {
			p = p.right;
		} else if (p.left != null) {
			p = p.left;
		} else {
			p = null;
		}
		if (p != null) {
			while (parent != null) {
				if (parent.left != null) {
					p.next = parent.left;
					break;
				} else if (parent.right != null) {
					p.next = parent.right;
					break;
				} else {
					parent = parent.next;
				}
			}
		}

		connect(root.right);
		connect(root.left);
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
		TreeLinkNode n8 = new TreeLinkNode(8);
		root.left = n2;
		root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.right = n6;
		n4.right = n7;
		n6.right = n8;
		connect(root);
		System.out.println(root);

	}

}
