package leetcodeoj;

import java.util.Stack;

import org.junit.Test;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * 
 * For example, this binary tree is symmetric:
 * 
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not: 
 *     1
 *    / \
 *   2   2
 *    \   \
 *     3    3
 * 
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * 
 * 
 */
public class SymmetricTree {

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> left = new Stack<TreeNode>();
		Stack<TreeNode> right = new Stack<TreeNode>();
		left.add(root.left);
		right.add(root.right);
		while (!left.isEmpty() && !right.isEmpty()) {
			TreeNode l = left.pop();
			TreeNode r = right.pop();
			if (l == null && r == null) {
				continue;
			}
			if (l == null || r == null) {
				return false;
			}
			if (l.val != r.val) {
				return false;
			}
			left.add(l.left);
			left.add(l.right);
			right.add(r.right);
			right.add(r.left);
		}
		return true;

		// return isSymmetric(root.left, root.right);
	}

	public boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}
		if (left.val != right.val) {
			return false;
		}
		return isSymmetric(left.left, right.right)
				&& isSymmetric(left.right, right.left);

	}

	@Test
	public void test() {
		TreeNode p0 = new TreeNode(1);
		TreeNode p1 = new TreeNode(2);
		TreeNode p2 = new TreeNode(2);
		TreeNode p3 = new TreeNode(3);
		TreeNode p4 = new TreeNode(4);
		TreeNode p5 = new TreeNode(4);
		TreeNode p6 = new TreeNode(3);
		p0.left = p1;
		p0.right = p2;
		p1.left = p3;
		p1.right = p4;
		p2.left = p5;
		p2.right = p6;

		System.out.println(isSymmetric(p0));
	}
}
