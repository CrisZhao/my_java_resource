package leetcodeoj;

import org.junit.Test;

/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 */
public class MinDepthOfBinaryTree {
	public int minDepth(TreeNode root) {

		if (root == null) {
			return 0;
		}
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		if (left == 0 && right == 0) {
			return 1;
		}
		if (left == 0) {
			return right + 1;
		}
		if (right == 0) {
			return left + 1;
		}
		return Math.min(left, right) + 1;
	}


	@Test
	public void test() {
		TreeNode l3 = new TreeNode(0);
		TreeNode l4 = new TreeNode(0);
		TreeNode l2 = new TreeNode(0);
		TreeNode l1 = new TreeNode(0);
		TreeNode l0 = new TreeNode(0);
		TreeNode r1 = new TreeNode(0);
		TreeNode lr = new TreeNode(0);
		lr.left = l0;
		lr.right = r1;
		l0.left = l1;
		l1.left = l2;
		l2.right = l3;
		l3.left = l4;
		System.out.println(minDepth(lr));
	}
}
