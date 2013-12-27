package leetcodeoj;

import org.junit.Test;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical and
 * the nodes have the same value.
 * 
 * @author Cris Zhao
 * 
 */
public class SameTree {

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

	}
	
	@Test
	public void test() {
		TreeNode p0 = new TreeNode(0);
		TreeNode p1 = new TreeNode(0);
		TreeNode p2 = new TreeNode(2);
		TreeNode p3 = new TreeNode(0);
		TreeNode p4 = new TreeNode(3);
		TreeNode p5 = new TreeNode(1);
		TreeNode p6 = new TreeNode(0);
		TreeNode q1 = new TreeNode(0);
		TreeNode q2 = new TreeNode(2);
		TreeNode q0 = new TreeNode(0);
		TreeNode q3 = new TreeNode(0);
		TreeNode q4 = new TreeNode(3);
		TreeNode q5 = new TreeNode(1);
		TreeNode q6 = new TreeNode(0);
		p0.left = p1;
		p0.right = p2;
		p1.left=p3;
		p3.right = p4;
		p4.left=p5;
		p4.right=p6;
		q0.left = q1;
		q0.right = q2;
		q1.left=q3;
		q3.right = q4;
		q4.left=q5;
		q4.right=q6;
		System.out.println(isSameTree(p0, q0));
	}
}
