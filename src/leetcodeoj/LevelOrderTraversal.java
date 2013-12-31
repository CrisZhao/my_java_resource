package leetcodeoj;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7},
 * 
 *    3 
 *   / \ 
 *  9  20 
 *     / \ 
 *    15  7 
 * return its level order traversal as:
 * 
 * [ [3], [9,20], [15,7] ]
 * 
 */
public class LevelOrderTraversal {
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return result;
		}
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		nodes.add(root);
		while (!nodes.isEmpty()) {
			ArrayList<TreeNode> subnodes = new ArrayList<TreeNode>();
			ArrayList<Integer> values = new ArrayList<Integer>();
			for (TreeNode node : nodes) {
				if (node == null) {
					continue;
				}
				values.add(node.val);
				subnodes.add(node.left);
				subnodes.add(node.right);
			}
			if (!values.isEmpty()) {
				result.add(values);
			}
			nodes = subnodes;
		}
		return result;

	}

	@Test
	public void test() {
		TreeNode node = new TreeNode(1);
		System.out.println(levelOrder(node));
	}
}
