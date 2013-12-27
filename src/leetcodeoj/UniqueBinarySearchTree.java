package leetcodeoj;

import org.junit.Test;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store
 * values 1...n?
 * 
 * For example, Given n = 3, there are a total of 5 unique BST's.
 * 
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 * 
 */
public class UniqueBinarySearchTree {
	public int numTrees(int n) {
		if (n == 0 || n == 1 || n == 2) {
			return n;
		}
		int result = 0;
		result += 2 * numTrees(n - 1);
		for (int i = 2; i < n ; i++) {
			result += numTrees(i - 1) * numTrees(n - i);
		}
		return result;
	}
	
	@Test
	public void test() {
		int a =3;
		System.out.println(numTrees(a));
	}

}
