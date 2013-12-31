package leetcodeoj;

import org.junit.Test;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * 
 */
public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {

		if (root == null) {
			return true;
		}
		if (height(root) == -1) {
			return false;
		}

		return true;
	}

	public int height(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = height(node.left);
		int right = height(node.right);
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return left > right ? left + 1 : right + 1;
	}

	@Test
	public void test() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(8);
		TreeNode n10 = new TreeNode(8);
		TreeNode n11 = new TreeNode(8);
		TreeNode n12 = new TreeNode(8);
		TreeNode n13 = new TreeNode(8);
//		TreeNode n14 = new TreeNode(8);
//		TreeNode n15 = new TreeNode(8);
//		TreeNode n16 = new TreeNode(8);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n4.left = n8;
		n4.right = n9;
		n7.left = n10;
		n7.right = n11;
		n10.left = n12;
		n10.right = n13;
		System.out.println(isBalanced(n1));

	}

}
