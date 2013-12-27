package leetcodeoj;

import org.junit.Test;

/**
 * Given an array where elements are sorted in ascending order, convert it to a
 * height balanced Binary Search Tree.
 * 
 * @author Cris Zhao
 * 
 */
public class SortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] num) {

		return buildTree(num, 0, num.length - 1);

	}

	public TreeNode buildTree(int[] num, int left, int right) {
		if (left > right) {
			return null;
		}
		int middle = (left + right) / 2;
		TreeNode node = new TreeNode(num[middle]);
		node.left = buildTree(num, left, middle - 1);
		node.right = buildTree(num, middle + 1, right);
		return node;
	}

	@Test
	public void test() {
		int num[] = { 1, 2, 3, 4, 5, 6 };
		TreeNode node = sortedArrayToBST(num);
		System.out.println(node);
	}
}
