package leetcodeoj;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3
 * 
 * return [3,2,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * 
 */
public class PostorderTraversal {

	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		result.addAll(postorderTraversal(root.left));
		result.addAll(postorderTraversal(root.right));
		result.add(root.val);
		return result;
	}

	public ArrayList<Integer> postorderTraversal1(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> nodes = new Stack<TreeNode>();
		nodes.add(root);
		TreeNode pre = null;
		while (!nodes.isEmpty()) {

			TreeNode node = nodes.pop();
			if (node == null) {
				continue;
			}
			if ((node.left == null && node.right == null)
					|| (pre != null && (pre == node.left || pre == node.right))) {
				result.add(node.val);
				pre = node;
			} else {
				nodes.push(node);
				nodes.push(node.right);
				nodes.push(node.left);
			}

		}

		return result;
	}

	@Test
	public void test() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		// TreeNode n7 = new TreeNode(7);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		// n3.right = n7;
		System.out.println(postorderTraversal1(n1));
	}
}
