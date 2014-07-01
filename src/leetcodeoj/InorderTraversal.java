package leetcodeoj;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example: Given binary tree {1,#,2,3},
 *  1
 *   \
 *    2
 *   /
 *  3 
 * return [1,3,2].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 */
public class InorderTraversal {

	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		return recursion(root);
	}

	public ArrayList<Integer> recursion(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		result.addAll(recursion(root.left));
		result.add(root.val);
		result.addAll(recursion(root.right));

		return result;
	}

	public ArrayList<Integer> iteration(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		while (!stack.isEmpty() || node != null) {

			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			result.add(node.val);
			node = node.right;
		}

		return result;
	}

	@Test
	public void test() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.right = n2;
		n2.left = n3;
		System.out.println(iteration(root));
	}
}
