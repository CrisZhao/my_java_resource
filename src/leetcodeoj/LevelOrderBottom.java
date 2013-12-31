package leetcodeoj;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes'
 * values. (ie, from left to right, level by level from leaf to root).
 * 
 * For example: Given binary tree 
 * {3,9,20,#,#,15,7}, 
 *     3 
 *    / \ 
 *   9  20 
 *      / \ 
 *     15  7 
 * return
 * its bottom-up level order traversal as: 
 * [ [15,7], [9,20], [3] ]
 * 
 */
public class LevelOrderBottom {

	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return result;
		}
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		nodes.add(root);
		while (!nodes.isEmpty()) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			ArrayList<TreeNode> subNodes = new ArrayList<TreeNode>();
			for (TreeNode treeNode : nodes) {
				if (treeNode == null) {
					continue;
				}
				line.add(treeNode.val);
				subNodes.add(treeNode.left);
				subNodes.add(treeNode.right);
			}
			if (!line.isEmpty()) {
				result.add(line);
			}
			nodes.clear();
			nodes.addAll(subNodes);
		}
		Collections.reverse(result);
		return result;
	}

	@Test
	public void test() {
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(7);
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		System.out.println(levelOrderBottom(n1));
	}

}
