package leetcodeoj;

import org.junit.Test;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p/>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p/>
 * Find the total sum of all root-to-leaf numbers.
 * <p/>
 * For example,
 * <p/>
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * <p/>
 * Return the sum = 12 + 13 = 25.
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {

        return sumNode(root, 0);
    }

    private int sumNode(TreeNode node, int pathSum) {
        if (node == null) {
            return 0;
        }
        pathSum = pathSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return pathSum;
        }
        return sumNode(node.left, pathSum) + sumNode(node.right, pathSum);

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

        System.out.println(sumNumbers(p0));
    }
}
