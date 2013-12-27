package leetcodeoj;

import org.junit.Test;

/**
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 * 
 * @author Cris Zhao
 * 
 */
public class MaxDepOfBinaryTree {

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int ldp = maxDepth(root.left);
		int rdp = maxDepth(root.right);
		return ldp > rdp ? ldp + 1 : rdp + 1;
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
		System.out.println(maxDepth(lr));
	}

}
