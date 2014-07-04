package leetcodeoj;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * For example: Given binary tree {1,#,2,3},
 *  1
 *   \
 *    2
 *   /
 *  3
 * return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * @author Cris Zhao
 * 
 */
public class PreorderTraversal {

	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		return iteration(root);
	}

	public ArrayList<Integer> iteration(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;

		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				result.add(node.val);
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			node = node.right;
		}

		return result;
	}

	public ArrayList<Integer> recursion(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		TreeNode node = root;
		result.add(node.val);
		result.addAll(recursion(node.left));
		result.addAll(recursion(node.right));
		return result;
	}

	@Test
	public void test() {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		root.right = n2;
		n2.left = n3;
		System.out.println(preorderTraversal(root));
	}

}
